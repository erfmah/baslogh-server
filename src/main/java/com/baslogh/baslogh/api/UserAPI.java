package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.*;
import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.service.CaseService;
import com.baslogh.baslogh.service.ReferralService;
import com.baslogh.baslogh.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.baslogh.baslogh.dto.UserActivateDTO;
import com.baslogh.baslogh.dto.UserDetailDTO;
import com.baslogh.baslogh.dto.UserDetailsWithCaseDTO;

import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;


@RequestMapping("api/v1/profile")
@RestController
public class UserAPI {

    @Autowired
    UserService userService;

    @Autowired
    CaseService caseService;

    @Autowired
    ReferralService referralService;

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
        UUID uid = UUID.fromString(id);
        Optional<User> user = userService.findById(uid);
        var cases = caseService.findByAuthor(user.get());
        System.out.println(cases.size());
        return cases;
    }

    @CrossOrigin
    @GetMapping("/listrefferals/{id}")
    public Set<Referral> findReferralls(@PathVariable("id") String id, HttpServletRequest request) {
        UUID uid = UUID.fromString(id);
        var cases = referralService.findByReciever(uid);
        return cases;
    }
    @CrossOrigin
    @GetMapping("/numOfSubmittedCase/{id}")
    public int numOfSubmittedCase(@PathVariable("id") String id,HttpServletRequest request) {
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
    public User editProfile(@RequestBody UserEditDTO userDTO, @PathVariable("id") String id, HttpServletRequest request) {
            UUID uid = UUID.fromString(id);
        Optional<User> user = userService.findById(uid);
        User user_ = userDTO.getUSer(user.get());
        if (userDTO.getPassword() != null && userDTO.getResetPassword().equals(userDTO.getPassword())) {
            String newPass = passwordEncoder.encode(userDTO.getPassword());
            user_.setPassword(newPass);
        }
        return userService.activate(user_);
    }

    @CrossOrigin
    @PostMapping("/deactive")
    @RolesAllowed("ROLE_ADMIN")
    public Response deactive(@RequestBody UserActivateDTO userToDeactive) {
        userService.deactive(userToDeactive.getUser().getId());
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @CrossOrigin
    @GetMapping("/details")
    @RolesAllowed("ROLE_ADMIN")
    public Set<UserDetailDTO> details() {
        HashSet<UserDetailDTO> details = new HashSet<>();
        for (User user: userService.listAccepted()) {
            var userDetail = new UserDetailDTO();
            userDetail.setUser(user);
            userDetail.setRequestedCasesCount(user.getCasesToDo().size());
            userDetail.setWrittenCasesCount(user.getCasesWritten().size());
            details.add(userDetail);
        }
        return details;
    }

    @CrossOrigin
    @GetMapping("/detail/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public UserDetailsWithCaseDTO profile(@PathVariable("id") String userId) {
        var user = userService.findById(UUID.fromString(userId)).get();
        var userDetail = new UserDetailsWithCaseDTO();
        userDetail.setUser(user);
        userDetail.setRequestedCasesCount(user.getCasesToDo().size());
        userDetail.setWrittenCasesCount(user.getCasesWritten().size());
        userDetail.setCases(user.getCasesWritten());
        return userDetail;
    }
}
