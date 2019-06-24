package com.baslogh.baslogh.dto;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.Referral;
import com.baslogh.baslogh.model.User;

import javax.persistence.Id;

public class ReferralDTO {

    Case refresnceCase;
    Referral referral;
    User author;
    User reciever;
    String content;

    public void setRefresnceCase(Case refresnceCase) {
        this.refresnceCase = refresnceCase;
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

    public Case getRefresnceCase() {
        return refresnceCase;
    }

    public Referral getReferral() {
        return referral;
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
