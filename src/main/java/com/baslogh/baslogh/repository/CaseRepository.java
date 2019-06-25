package com.baslogh.baslogh.repository;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CaseRepository extends JpaRepository<Case, UUID> {
    List<Case> findAllByAuthor(User user);
    List<Case> findAllByReceiver(User user);
    Set<Case> findByCreatedAtGreaterThanAndCreatedAtLessThan(Date from, Date to);
    Optional<Case> findById(UUID id);
}
