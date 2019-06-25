package com.baslogh.baslogh.repository;

import com.baslogh.baslogh.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
}
