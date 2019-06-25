package com.baslogh.baslogh.service;

import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.repository.ReferralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ReferralService {
    @Autowired
    ReferralRepository referralRepository;

    public Referral save (Referral referral){
        return referralRepository.save(referral);
    }

    public List<Referral> findByAuthor(User user){
        return referralRepository.findAllByAuthor(user);
    }

    public Set<Referral> findByReciever(UUID id){
        return referralRepository.findByReceiver_Id(id);
    }

    public Set<Referral> findAllCaseReferralsByReferralId(UUID id) {
        var ref = referralRepository.findById(id).get();
        var refs = referralRepository.findByRefresnceCase_Id(ref.getRefresnce().getId());
        return refs;
    }

    public Referral findById(UUID id) {
        return referralRepository.findById(id).get();
    }

//    public Set<Referral> findByRefId(UUID id) {return referralRepository.findAllByRefresnce_Id(id);}
}
