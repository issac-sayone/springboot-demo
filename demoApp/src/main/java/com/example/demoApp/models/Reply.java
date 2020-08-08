package com.example.demoApp.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="dashboard_reply")
public class Reply extends BaseModel {

    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Conversation conversation;

    public Reply() {
    }

    public Reply(String text, User user, Conversation conversation) {
        this.text = text;
        this.user = user;
        this.conversation = conversation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
