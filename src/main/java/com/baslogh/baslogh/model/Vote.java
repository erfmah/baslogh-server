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

    @ManyToOne
    @JoinColumn(name = "case")
    private Case caseOf;

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

    @Column(name = "type")
    private Boolean type;
}