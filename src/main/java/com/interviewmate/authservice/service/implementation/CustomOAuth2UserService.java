package com.interviewmate.authservice.service.implementation;

import com.interviewmate.authservice.entity.User;
import com.interviewmate.authservice.enums.Provider;
import com.interviewmate.authservice.enums.Role;
import com.interviewmate.authservice.repository.UserRepository;
import com.interviewmate.authservice.security.oauth2.OAuth2UserInfo;
import com.interviewmate.authservice.security.oauth2.Oauth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    /**
     * called by spring security after it fetches the user's profile from
     * the OAuth2 provider google & GitHub, this method:
     * 1. extracts attributes from the provider response
     * 2. creates or updates the local user record
     * 3. returns a DefaultOAuth2User that spring security places in the security-context
     *
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            // warp non-auth exceptions so spring security's failure handler is invoked
            log.error("Failed to process OAuth2 user from provider [{}] : {}", userRequest.getClientRegistration().getRegistrationId(), e.getMessage(), e);
            throw new InternalAuthenticationServiceException(e.getMessage(), e);
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest request, OAuth2User oAuth2User) {
        String registrationId = request.getClientRegistration().getRegistrationId();
        String userNameAttributeName = request.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // normalise provider specific attribute maps into a consistent interface
        OAuth2UserInfo userInfo = Oauth2UserInfoFactory.getOauth2UserInfo(registrationId, oAuth2User.getAttributes());

        if (!StringUtils.hasText(userInfo.getEmail())) {
            // GitHub users with private emails will hit this
            // instruct them to make their email public or add a fallback strategy.
            throw new OAuth2AuthenticationException(
                    new OAuth2Error("email_not_found"),
                    "no email return from " + registrationId + ". Please make your email public in your " + registrationId + " account settings."
            );
        }

        Provider provider = Provider.valueOf(registrationId.toUpperCase());

        // primary lookup: by provider + providerId
        User user = userRepository.findByProviderAndProviderId(provider, userInfo.getId())
                .map(existing -> updateExistingUser(existing, userInfo))
                .orElseGet(() -> {
                    // secondary check: email exists under a different provider
                    if (userRepository.existsByEmail(userInfo.getEmail())) {
                        throw new OAuth2AuthenticationException(
                                new OAuth2Error("email_conflict"),
                                "An account with email [" + userInfo.getEmail() + "] already exits. please log in with your original method."
                        );
                    }
                    return registerNewUser(provider, userInfo);
                });

        Map<String, Object> enrichedAttributes = new HashMap<>(oAuth2User.getAttributes());
        enrichedAttributes.put("email", user.getEmail()); // normalize: always present

        return new DefaultOAuth2User(List.of(new SimpleGrantedAuthority(user.getRole().name())), enrichedAttributes, userNameAttributeName);
    }

    private User registerNewUser(Provider provider, OAuth2UserInfo userInfo) {
        // TODO: remove or comment on production
        log.info("registration new {} user: {}", provider, userInfo.getEmail());

        User newUser = User.builder()
                .email(userInfo.getEmail())
                .name(userInfo.getName())
                .image(userInfo.getImageUrl())
                .provider(provider)
                .providerId(userInfo.getId())
                .role(Role.ROLE_USER) // default role for OAuth2 registrations
                .build();

        return userRepository.save(newUser);
    }

    private User updateExistingUser(User user, OAuth2UserInfo userInfo) {
        // refresh display name and avatar on every login so they stay in sync
        user.setName(userInfo.getName());
        user.setImage(userInfo.getImageUrl());
        return userRepository.save(user);
    }
}
