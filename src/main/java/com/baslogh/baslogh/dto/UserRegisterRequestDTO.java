package com.baslogh.baslogh.dto;


import com.baslogh.baslogh.model.Role;

import java.util.List;

public class  UserRegisterRequestDTO {
  
  private String email;
  List<Role> roles;
  private String password;
  private String firstname;

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  private String lastname;
  public void setPassword(String password) {
    this.password = password;
  }


  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

}
