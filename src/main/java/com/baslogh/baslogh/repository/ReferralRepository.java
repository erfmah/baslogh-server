package com.baslogh.baslogh.repository;

import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ReferralRepository extends JpaRepository<Referral, UUID> {
    public List<Referral> findAllByAuthor(User user);
    Set<Referral> findByReceiver_Id(UUID id);
    Set<Referral> findByRefresnceCase_Id(UUID id);
    Optional<Referral> findById(UUID id);
}
