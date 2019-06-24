package com.baslogh.baslogh.repository;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CaseRepository extends JpaRepository<Case, UUID> {
    public List<Case> findAllByAuthor(User user);
    public List<Case> findAllByReceiver(User user);

}
