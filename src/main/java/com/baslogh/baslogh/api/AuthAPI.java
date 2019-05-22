package com.baslogh.baslogh.api;

import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1/user")
@RestController
public class AuthAPI {

    private final AuthService authService;

    @Autowired
    public AuthAPI(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    @CrossOrigin
    public User register( @RequestBody User user) {
        return authService.register(user);
    }
}
