package com.baslogh.baslogh.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;
import java.util.UUID;

enum Subject
{
    grievance, criticism, proposal, request;
}


@Entity
@Table(name = "Cases")
public class Case implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final UUID id;

    @ManyToOne
    @JoinColumn(name = "author")
    User author;

    @ManyToOne
    @JoinColumn(name = "receiver")
    User receiver;

    @Column(name = "date")
    Date submitDate;

    @Column(name = "subject")
    String subject;

    @Column(name = "title")
    String title;

    public UUID getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public User getReceiver() {
        return receiver;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return text;
    }

    public String getPath() {
        return path;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.text = content;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "content")
    String text;

    @Column(name = "path")
    String path;


    public Case(@JsonProperty("id") UUID id,
//                @JsonProperty("author") User author,
//                @JsonProperty("receiver")User receiver,
                @JsonProperty("submitDate")Date submitDate,
                @JsonProperty("subject")String subject,
                @JsonProperty("title")String title,
                @JsonProperty("text")String content,
                @JsonProperty("path")String path) {
        this.id = id;
//        this.author = author;
//        this.receiver = receiver;
        this.submitDate = submitDate;
        this.subject = subject;
        this.title = title;
        this.text = content;
        this.path = path;
    }
}
