package com.baslogh.baslogh.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Referral")
public class Referral {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "refrence")
    Case refresnceCase;
    @ManyToOne
    @JoinColumn(name = "parent")
    Referral parent;
    @ManyToOne
    @JoinColumn(name = "author")
    User author;
    @ManyToOne
    @JoinColumn(name = "reciever")
    User receiver;
    @Column
    String content;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRefresnce(Case refresnce) {
        this.refresnceCase = refresnce;
    }

    public void setParent(Referral parent) {
        this.parent = parent;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public User getReceiver() {
        return receiver;
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

    public Referral getParent() {
        return parent;
    }
}
