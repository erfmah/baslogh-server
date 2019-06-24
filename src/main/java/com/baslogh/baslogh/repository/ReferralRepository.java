package com.baslogh.baslogh.repository;

import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReferralRepository extends JpaRepository<Referral, UUID> {
    public List<Referral> findAllByAuthor(User user);

}
