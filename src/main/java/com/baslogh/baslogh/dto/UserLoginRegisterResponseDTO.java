package com.baslogh.baslogh.dto;


import com.baslogh.baslogh.model.Role;

import java.util.List;
import java.util.UUID;

public class UserLoginRegisterResponseDTO {

  private UUID id;
  private String email;
  private String token;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
