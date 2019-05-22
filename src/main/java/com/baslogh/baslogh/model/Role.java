package com.baslogh.baslogh.model;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
  ROLE_STUDENT, ROLE_ADMIN, ROLE_CLERK, ROLE_PROF;

  public String getAuthority() {
    return name();
  }

}
