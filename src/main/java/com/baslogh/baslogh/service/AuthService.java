package com.baslogh.baslogh.service;

import com.baslogh.baslogh.dao.UserDao;
import com.baslogh.baslogh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserDao userDao;

    @Autowired
    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User register(User user) {
        userDao.create(user);
        return user;
    }
}
