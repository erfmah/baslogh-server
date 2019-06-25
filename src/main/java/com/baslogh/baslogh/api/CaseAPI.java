package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.CaseDTO;
import com.baslogh.baslogh.dto.ReferralDTO;
import com.baslogh.baslogh.dto.CaseFilterDTO;
import com.baslogh.baslogh.dto.CaseFilterRes;
import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.model.Subject;
import com.baslogh.baslogh.service.CaseService;
import com.baslogh.baslogh.service.ReferralService;
import com.baslogh.baslogh.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    @PostMapping("/filter")
    @CrossOrigin
    public CaseFilterRes filterCases(@RequestBody CaseFilterDTO caseFilterDTO) {
        var cases = caseService.filterBetweenDates(caseFilterDTO.getFrom(), caseFilterDTO.getTo());
        var res = new CaseFilterRes();
        int g = 0;
        int c = 0;
        int p = 0;
        int r = 0;
        for (var case_:cases) {
            if (case_.getSubject().equals(Subject.grievance.toString())) {
                g++;
            }
            if (case_.getSubject().equals(Subject.criticism.toString())) {
                c++;
            }
            if (case_.getSubject().equals(Subject.proposal.toString())) {
                p++;
            }
            if (case_.getSubject().equals(Subject.request.toString())) {
                r++;
            }
        }
        res.setCriticism(c);
        res.setRequest(r);
        res.setProposal(p);
        res.setGrievance(g);
        res.setTotal(g+p+r+c);
        return res;
    }


}
