package com.baslogh.baslogh.security;

import com.baslogh.baslogh.repository.UserRepository;
import com.baslogh.baslogh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + s + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(s)//
                .password(user.getPassword())//
                .authorities(user.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
