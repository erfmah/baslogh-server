package com.baslogh.baslogh.repository;

import com.baslogh.baslogh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  boolean existsByEmail(String username);

  User findByEmail(String username);

  @Transactional
  void deleteByEmail(String username);

}
