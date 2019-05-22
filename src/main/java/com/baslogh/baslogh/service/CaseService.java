package com.baslogh.baslogh.service;

import com.baslogh.baslogh.dao.CaseDao;
import com.baslogh.baslogh.model.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseSubmissionService {
    private final CaseDao caseDao;

    @Autowired
    public CaseSubmissionService(CaseDao caseDao) {
        this.caseDao = caseDao;
    }

    public Case submitCase (Case newCase){

        caseDao.create(newCase);
        return newCase;
    }
}
