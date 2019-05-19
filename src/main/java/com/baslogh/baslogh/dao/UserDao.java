package com.baslogh.baslogh.dao;

import com.baslogh.baslogh.model.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;



@Repository
public class UserDao extends AbstractJPADAO<User>  {
    public UserDao() {
        setClazz(User.class);
    }
}
