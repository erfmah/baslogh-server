package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.UserActivateDTO;
import com.baslogh.baslogh.dto.UserDetailDTO;
import com.baslogh.baslogh.dto.UserDetailsWithCaseDTO;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


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
