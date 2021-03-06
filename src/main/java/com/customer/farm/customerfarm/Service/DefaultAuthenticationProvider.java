package com.customer.farm.customerfarm.Service;

import com.customer.farm.customerfarm.Entity.Users;
import com.customer.farm.customerfarm.Repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private final UsersRepository appUserRepository;

    public DefaultAuthenticationProvider(UsersRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        if (authentication.getName() == null || authentication.getCredentials() == null) {
            return null;
        }

        if (authentication.getName().isEmpty() || authentication.getCredentials().toString().isEmpty()) {
            return null;
        }

        final Optional<Users> appUser = this.appUserRepository.findByUsername(authentication.getName());

        if (appUser.isPresent()) {
            final Users user = appUser.get();
            final String providedUserEmail = authentication.getName();
            final Object providedUserPassword = authentication.getCredentials();

            if (providedUserEmail.equalsIgnoreCase(user.getUsername())
                    && providedUserPassword.equals(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
            }
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
