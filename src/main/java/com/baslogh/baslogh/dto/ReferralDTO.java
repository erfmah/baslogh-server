package com.baslogh.baslogh.dto;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;

import javax.persistence.Id;

public class ReferralDTO {

    Referral parent;
    User author;
    User reciever;
    String content;
    String status;

    public void setParent(Referral parent) {
        this.parent = parent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Referral getParent() {
        return parent;
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
}
