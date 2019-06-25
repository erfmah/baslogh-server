package com.baslogh.baslogh.api;

import com.baslogh.baslogh.dto.CaseFilterDTO;
import com.baslogh.baslogh.dto.CaseFilterRes;
import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.Subject;
import com.baslogh.baslogh.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("api/v1/case")
@RestController
public class CaseAPI {

    private final CaseService caseService;

    @Autowired
    public CaseAPI(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping
    @CrossOrigin
    public Case submitCase(@RequestBody Case newCase) {
        return caseService.submitCase(newCase);
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
