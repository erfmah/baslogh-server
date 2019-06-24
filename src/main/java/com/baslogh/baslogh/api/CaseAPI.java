package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.CaseDTO;
import com.baslogh.baslogh.dto.ReferralDTO;
import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.service.CaseService;
import com.baslogh.baslogh.service.ReferralService;
import com.baslogh.baslogh.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("api/v1/case")
@RestController
public class CaseAPI {

    @Autowired
    private CaseService caseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReferralService referralService;

    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin
    @PostMapping("/submit")
    public Case submitCase(@RequestBody CaseDTO newCase) {
        Case case_ = modelMapper.map(newCase, Case.class);
        String authEmail = (newCase.getAuthor()).getEmail();
        String recEmail  = (newCase.getReceiver()).getEmail();
        User author = userService.findByEmail(authEmail);
        User reciever = userService.findByEmail(recEmail);
        case_.setAuthor(author);
        case_.setReceiver(reciever);
        //case_= caseService.setAuthor(caseService.submitCase(case_), userService.findByEmail(request.getRemoteUser()));
        return caseService.submitCase(case_);
    }

    @CrossOrigin
    @PostMapping("/addReferral")
    public Referral addReferral (ReferralDTO referralDTO){
        Referral referral = modelMapper.map(referralDTO, Referral.class);
        referralService.save(referral);
        return  referral;
    }




}
