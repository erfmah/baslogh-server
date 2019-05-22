package com.baslogh.baslogh.api;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
