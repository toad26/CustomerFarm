package com.customer.farm.customerfarm.Service;

import com.customer.farm.customerfarm.Entity.Users;
import com.customer.farm.customerfarm.Repository.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class DefaultUserDetailsService implements UserDetailsService {

    private final UsersRepository appUserRepository;

    public DefaultUserDetailsService(UsersRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<Users> userEntity = appUserRepository.findUserByUsername(username);

        if (userEntity.isPresent()) {
            final Users appUser = userEntity.get();

            return new User(appUser.getUsername(),
                    passwordNoEncoding(appUser),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }

        return null;
    }

    private String passwordNoEncoding(Users appUser) {
        // you can use one of bcrypt/noop/pbkdf2/scrypt/sha256
        // more: https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
        return "{noop}" + appUser.getPassword();
    }
}
