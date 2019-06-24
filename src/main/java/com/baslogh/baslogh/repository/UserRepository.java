package com.baslogh.baslogh.repository;

import com.baslogh.baslogh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  boolean existsByEmail(String username);
  User findByEmail(String email);
  void deleteByEmail(String username);
  Optional<User> findById(UUID id);
  List<User> findAll();
  List<User> findByAcceptedIsNull();
}
