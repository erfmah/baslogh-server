package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.*;
import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.service.AuthService;
import com.baslogh.baslogh.service.CaseService;
import com.baslogh.baslogh.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("api/v1/profile")
@RestController
public class UserAPI {

    @Autowired
    UserService userService;

    @Autowired
    CaseService caseService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

    @CrossOrigin
    @GetMapping("/listSubmittedCase")
    public List<Case> findSubmittedCase(HttpServletRequest request) {
        String email = request.getRemoteUser();
        User user = userService.findByEmail(email);
        var cases = caseService.findByAuthor(user);
   //List<CaseDTO>  res = new
        return cases;
    }

    @CrossOrigin
    @GetMapping("/listReferredCase")
    public List<Case> findReferredCase(HttpServletRequest request) {
        String email = request.getRemoteUser();
        User user = userService.findByEmail(email);
        var cases = caseService.findByReceiver(user);
        return cases;
    }

    @CrossOrigin
    @PostMapping("/editProfile")
    public User editProfile(@RequestBody UserDTO userDTO, HttpServletRequest request){
        String email = request.getRemoteUser();
        User user = userService.findByEmail(email);
        user = userDTO.getUSer(user);
        if(userDTO.getPassword()!= null) {
            String newPass = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(newPass);
        }
        return userService.activate(user);
    }
}
