package com.baslogh.baslogh.dto;

import com.baslogh.baslogh.model.User;

public class UserDTO {
    String firstname;
    String lastname;
    String Email;
    String password;

    public User getUSer(User user){
        if(firstname!= null)
        user.setFirstname(firstname);
        if(lastname!= null)
        user.setLastname(lastname);
        if(Email!= null)
        user.setEmail(Email);

        return user;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO() {
    }
}
