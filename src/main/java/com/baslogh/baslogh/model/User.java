package com.baslogh.baslogh.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final UUID id;
    @Column(name = "firstname")
    private final String firstname;
    @Column(name = "lastname")
    private final String lastname;

    public User(@JsonProperty("id") UUID id, @JsonProperty("firstname") String firstname, @JsonProperty("lastname") String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
