package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.*;
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

import javax.ws.rs.core.Response;
import java.util.UUID;

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
        Referral referral = new Referral();
        referral.setAuthor(author);
        referral.setReceiver(reciever);
        referral.setRefresnce(case_);
        referral.setContent(case_.getContent());
        caseService.submitCase(case_);
        referralService.save(referral);
        return case_;
    }

    @CrossOrigin
    @PostMapping("/addreferral")
    public Referral addReferral (@RequestBody ReferralDTO referralDTO){
        Referral referral = new Referral();
        Referral parent = referralService.findById(referralDTO.getParent().getId());
        User receiver = userService.findByEmail(referralDTO.getReciever().getEmail());
        referral.setParent(parent);
        referral.setReceiver(receiver);
        referral.setAuthor(referralDTO.getAuthor());
        referral.setContent(referralDTO.getContent());
        var case_ = caseService.findById(parent.getRefresnce().getId());
        case_.setStatus(referralDTO.getStatus());
        referral.setRefresnce(caseService.submitCase(case_));
        return referralService.save(referral);
    }

    @CrossOrigin
    @GetMapping("/refpage/{id}")
    public ReferralPageDTO addReferral (@PathVariable("id") String id){
        ReferralPageDTO referralPageDTO = new ReferralPageDTO();
        var referals = referralService.findAllCaseReferralsByReferralId(UUID.fromString(id));
        referralPageDTO.setReferrals(referals);
        referralPageDTO.setRefCase(caseService.findById(referals.iterator().next().getRefresnce().getId()));
        return referralPageDTO;
    }


//    @CrossOrigin
//    @GetMapping("/caseinfo/{id}")
//    public ReferralPageDTO getCase (@PathVariable("id") String id){
//        ReferralPageDTO referralPageDTO = new ReferralPageDTO();
//        var case_ = caseService.findById(UUID.fromString(id));
//        var referals = referralService.findByRefId(case_.getId());
//        referralPageDTO.setReferrals(referals);
//        referralPageDTO.setRefCase(case_);
//        return referralPageDTO;
//    }

    @CrossOrigin
    @GetMapping("/caseinfo/{id}")
    public ReferralPageDTO caseInfo (@PathVariable("id") String id){
        ReferralPageDTO referralPageDTO = new ReferralPageDTO();
        var case_ = caseService.findById(UUID.fromString(id));
        var referals = referralService.findByRef(case_.getId());
        referralPageDTO.setReferrals(referals);
        referralPageDTO.setRefCase(case_);
        return referralPageDTO;
    }

    @CrossOrigin
    @GetMapping("/like/{id}")
    public Response like (@PathVariable("id") String id){
        return Response.status(200).build();
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
