package com.baslogh.baslogh.api;

import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/v1/user")
@RestController
public class AuthAPI {

    private final AuthService authService;

    @Autowired
    public AuthAPI(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public User register( @RequestBody User user) {
        return authService.register(user);
    }
}
