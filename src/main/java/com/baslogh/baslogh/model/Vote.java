package com.baslogh.baslogh.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "votes")
public class Vote implements Serializable {
    public Vote() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Case caseOf;

    @ManyToOne
    @JoinColumn(name = "voter")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Case getCaseOf() {
        return caseOf;
    }

    public void setCaseOf(Case caseOf) {
        this.caseOf = caseOf;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    @Column(name = "vote_type")
    private Boolean type;
}
