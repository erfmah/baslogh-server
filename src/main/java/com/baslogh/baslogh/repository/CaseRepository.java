package com.baslogh.baslogh.repository;

import com.baslogh.baslogh.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CaseRepository extends JpaRepository<Case, UUID> {

}
