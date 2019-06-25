package com.baslogh.baslogh.service;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.User;
import com.baslogh.baslogh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User activate(User user) {
        user.activate();
        userRepository.save(user);
        return user;
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> listUnaccepted() {
        return userRepository.findByAcceptedIsNull();
    }

    public List<User> listAccepted() {
        return userRepository.findByAcceptedNotNull();
    }

    public void deactive(UUID id) {
        userRepository.deleteById(id);
    }

    public int getCasesWrittenCount(UUID id) {
        return this.findById(id).get().getCasesWritten().size();
    }

    public int getCasesToDoCount(UUID id) {
        return this.findById(id).get().getCasesToDo().size();
    }

    public Set<Case> getCases(UUID id) {
        return this.findById(id).get().getCasesWritten();
    }
}

