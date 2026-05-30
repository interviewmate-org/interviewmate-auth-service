package com.interviewmate.authservice.security.oauth2;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

import java.util.Map;

public class Oauth2UserInfoFactory {

<<<<<<< HEAD
=======
    private Oauth2UserInfoFactory() {
       
    }


>>>>>>> origin/feature/oAuth2
    public static OAuth2UserInfo getOauth2UserInfo(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId.toLowerCase()) {
            case "google" -> new GoogleOAuth2UserInfo(attributes);
            case "github" -> new GitHubOAuth2UserInfo(attributes);
            default -> throw new OAuth2AuthenticationException(
                    new OAuth2Error("unsupported_provider"),
                    "Provider [" + registrationId + "] is nto currently supported"
            );
        };
    }
}
