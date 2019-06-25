package com.baslogh.baslogh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "firstname")
    private String firstname;

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Case> casesWritten;

    @JsonIgnore
    public Set<Case> getCasesWritten() {
        return casesWritten;
    }

    @JsonIgnore
    public Set<Case> getCasesToDo() {
        return casesToDo;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Case> casesToDo;

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

    @Column(name = "lastname")
    private String lastname;
    @JsonIgnore
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date accepted;

    private String currentToken;

    public String getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(String currentToken) {
        this.currentToken = currentToken;
    }


    public Date getAccepted() {
        return accepted;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void activate() {
        this.accepted = new Date();
    }
}
