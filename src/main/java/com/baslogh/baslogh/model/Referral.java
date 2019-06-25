package com.baslogh.baslogh.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Referral")
public class Referral {
    @Id
    @Column
    UUID id;

    @ManyToOne
    @JoinColumn(name = "refrence")
    Case refresnceCase;
    @OneToOne
    Referral referral;
    @Column
    User author;
    @Column
    User reciever;
    @Column
    String content;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRefresnce(Case refresnce) {
        this.refresnceCase = refresnce;
    }

    public void setReferral(Referral referral) {
        this.referral = referral;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public User getReciever() {
        return reciever;
    }

    public String getContent() {
        return content;
    }

    public UUID getId() {
        return id;
    }

    public Case getRefresnce() {
        return refresnceCase;
    }

    public Referral getReferral() {
        return referral;
    }
}
