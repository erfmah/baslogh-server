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
import java.util.Optional;
import java.util.UUID;


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
    @GetMapping("/listSubmittedCase/{id}")
    public List<Case> findSubmittedCase(@PathVariable("id") String id,HttpServletRequest request) {
        System.out.println("get sub list");

        String email = "er.mahmoudzadeh@gmail.com";
        UUID uid = UUID.fromString(id);
        Optional<User> user = userService.findById(uid);
        var cases = caseService.findByAuthor(user.get());
        System.out.println(cases.size());
        return cases;
    }

    @CrossOrigin
    @GetMapping("/listReferredCase/{id}")
    public List<Case> findReferredCase(@PathVariable("id") String id, HttpServletRequest request) {
        System.out.println("get ref list");
        UUID uid = UUID.fromString(id);
        Optional<User> user = userService.findById(uid);
        var cases = caseService.findByReceiver(user.get());
        System.out.println(cases.size());
        return cases;
    }
    @CrossOrigin
    @GetMapping("/numOfSubmittedCase/{id}")
    public int numOfSubmittedCase(@PathVariable("id") String id,HttpServletRequest request) {
        System.out.println("get sub list");

        String email = "er.mahmoudzadeh@gmail.com";
        UUID uid = UUID.fromString(id);
        Optional<User> user = userService.findById(uid);
        var cases = caseService.findByAuthor(user.get());
        System.out.println(cases.size());
        return cases.size();
    }
    @CrossOrigin
    @GetMapping("/getInfo/{id}")
    public User getInfo(@PathVariable("id") String id, HttpServletRequest request) {
        UUID uid = UUID.fromString(id);
        Optional<User> user = userService.findById(uid);
        return user.get();
    }

    @CrossOrigin
    @PostMapping("/editProfile/{id}")
    public User editProfile(@RequestBody UserDTO userDTO,@PathVariable("id") String id, HttpServletRequest request){
        UUID uid = UUID.fromString(id);
        Optional<User> user = userService.findById(uid);
        User user_ = userDTO.getUSer(user.get());
        if(userDTO.getPassword()!= null) {
            String newPass = passwordEncoder.encode(userDTO.getPassword());
            user_.setPassword(newPass);
        }
        return userService.activate(user_);
    }

}
