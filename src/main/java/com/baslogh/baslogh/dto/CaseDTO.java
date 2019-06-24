package com.baslogh.baslogh.dto;

import com.baslogh.baslogh.model.Case;
import com.baslogh.baslogh.model.Subject;
import com.baslogh.baslogh.model.User;



import java.util.Date;

public class CaseDTO {

    User receiver;
    User author;
    Date submitDate;
    Subject subject;
    String title;
    String text;

    public CaseDTO() {
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCreatedAt(Date createdAt) {
        this.submitDate = createdAt;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public Date getCreatedAt() {
        return submitDate;
    }

    public String getText() {
        return text;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getReceiver() {
        return receiver;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

}
