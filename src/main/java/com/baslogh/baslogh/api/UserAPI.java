package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.UserActivateDTO;
import com.baslogh.baslogh.dto.UserLoginRequestDTO;
import com.baslogh.baslogh.dto.UserRegisterRequestDTO;
import com.baslogh.baslogh.dto.UserLoginRegisterResponseDTO;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.service.AuthService;
import com.baslogh.baslogh.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RequestMapping("api/v1/profile")
@RestController
public class UserAPI {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/activate")
    @RolesAllowed("ROLE_ADMIN")
    public User activate(@RequestBody UserActivateDTO userToActivate) {
        var user = userService.findById(userToActivate.getUser().getId()).orElse(null);
        if (user != null) {
            userService.activate(user);
        }
        return user;
    }

    @CrossOrigin
    @GetMapping("/listunaccepted")
    @RolesAllowed("ROLE_ADMIN")
    public List<User> listUnacceptedUsers() {
        var users = userService.listUnaccepted();
        return users;
    }

}
