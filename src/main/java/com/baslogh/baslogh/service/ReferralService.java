package com.baslogh.baslogh.service;

import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.repository.ReferralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
