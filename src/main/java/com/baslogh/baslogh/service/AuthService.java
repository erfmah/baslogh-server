package com.baslogh.baslogh.service;

import com.baslogh.baslogh.exception.CustomException;
import com.baslogh.baslogh.repository.UserRepository;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;


    public User register(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            user.setCurrentToken(jwtTokenProvider.createToken(user.getEmail(), user.getRoles()));
            return user;
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public User login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            User loggedIn = userRepository.findByEmail(email);
            loggedIn.setCurrentToken(jwtTokenProvider.createToken(email, userRepository.findByEmail(email).getRoles()));
            return loggedIn;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
