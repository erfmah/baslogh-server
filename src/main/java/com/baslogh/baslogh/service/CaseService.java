package com.baslogh.baslogh.service;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class CaseService {
    @Autowired
    private CaseRepository caseRepository;
    public Case submitCase (Case newCase){
        caseRepository.save(newCase);
        return newCase;
    }
    public Set<Case> filterBetweenDates (Date from, Date to){
        return caseRepository.findByCreatedAtGreaterThanAndCreatedAtLessThan(from, to);
    }
}
