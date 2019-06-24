package com.baslogh.baslogh.service;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {
    @Autowired
    private CaseRepository caseRepository;
    public Case submitCase (Case newCase){
        caseRepository.save(newCase);
        return newCase;
    }
    public Case setAuthor(Case case_, User author) {
        case_.setAuthor(author);
        return caseRepository.save(case_);
    }
    public List<Case> findByAuthor(User user){
        return caseRepository.findAllByAuthor(user);
    }
    public List<Case> findByReceiver(User user){
        return caseRepository.findAllByReceiver(user);
    }
}
