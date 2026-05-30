package com.interviewmate.authservice.service.implementation;

import com.interviewmate.authservice.entity.User;
import com.interviewmate.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepo;


    /**
     * func UserDetails loadUserByUsername(@param String username)
     * function @param username but change the name into our project requirement in @param email
     * This method is invoked by security when a user tries to authenticate. It loads the user's details
     * (email, password, role) from database.
     *
     * @param email the email entered by the user.
     * @return UserDetails object containing email, password and authorities(roles).
     * @throws UsernameNotFoundException if the user with the given email is not found
     */
    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        User exitsUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found: " + email));

        // TODO: remove in production and when push to the main branch
        log.info("Loading user by email: {}", email);

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(exitsUser.getRole().name()));

        // TODO: remove in production and when push to the main branch
        log.info("Loaded authorities: {}", exitsUser.getEmail());

        return new org.springframework.security.core.userdetails.User(
                exitsUser.getEmail(),
                exitsUser.getPassword(),
                authorities
        );
    }
}
