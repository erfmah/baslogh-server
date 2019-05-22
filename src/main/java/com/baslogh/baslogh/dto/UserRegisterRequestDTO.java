package com.baslogh.baslogh.dto;


import com.baslogh.baslogh.model.Role;

import java.util.List;

public class UserRegisterRequestDTO {
  
  private String email;
  List<Role> roles;
  private String password;

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
