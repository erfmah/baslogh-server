package com.baslogh.baslogh.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "Cases")
public class Case implements Serializable {
    public Case() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "author")
    User author;

    @ManyToOne
    @JoinColumn(name = "receiver")
    User receiver;

    @Column(name = "subject")
    String subject;

    @Column(name = "title")
    java.lang.String title;

    @Column(name = "status" )
    String status = Status.open.toString() ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public Date getCreatedAt() {
        return createdAt;
    }

    public UUID getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public User getReceiver() {
        return receiver;
    }


    public String getSubject() {
        return subject;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public java.lang.String getContent() {
        return text;
    }

    public java.lang.String getPath() {
        return path;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public void setContent(java.lang.String content) {
        this.text = content;
    }

    public void setPath(java.lang.String path) {
        this.path = path;
    }

    @Column(name = "content")
    java.lang.String text;

    @Column(name = "path")
    java.lang.String path;

    public java.lang.String getText() {
        return text;
    }

    public void setText(java.lang.String text) {
        this.text = text;
    }

}
