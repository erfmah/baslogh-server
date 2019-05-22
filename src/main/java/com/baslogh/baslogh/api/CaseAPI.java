package com.baslogh.baslogh.api;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.service.CaseSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/case")
@RestController
public class CaseAPI {

    private final CaseSubmissionService caseSubmissionService;

    @Autowired
    public CaseAPI(CaseSubmissionService caseSubmissionService) {
        this.caseSubmissionService = caseSubmissionService;
    }

    @PostMapping
    @CrossOrigin
    public Case submitCase(@RequestBody Case newCase) {
        return caseSubmissionService.submitCase(newCase);
    }


}
