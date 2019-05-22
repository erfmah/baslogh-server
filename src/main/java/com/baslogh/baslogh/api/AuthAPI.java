package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.UserLoginRequestDTO;
import com.baslogh.baslogh.dto.UserRegisterRequestDTO;
import com.baslogh.baslogh.dto.UserLoginRegisterResponseDTO;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.service.AuthService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1/user")
@RestController
public class AuthAPI {
    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public UserLoginRegisterResponseDTO signup(@RequestBody UserRegisterRequestDTO user) {
         var createdUser = authService.register(modelMapper.map(user, User.class));
         var response = new UserLoginRegisterResponseDTO();
         response.setToken(createdUser.getCurrentToken());
         response.setEmail(createdUser.getEmail());
         response.setId(createdUser.getId());
         return response;
    }

    @PostMapping("/login")
    public UserLoginRegisterResponseDTO login(@RequestBody UserLoginRequestDTO user) {
        var email = user.getEmail();
        var password = user.getPassword();
        var response = new UserLoginRegisterResponseDTO();
        var loggedIn = authService.login(email, password);
        response.setToken(loggedIn.getCurrentToken());
        response.setEmail(loggedIn.getEmail());
        response.setId(loggedIn.getId());
        return response;
    }

    @PostMapping("/validated")
    public String validated() {
        return "ok";
    }


}
