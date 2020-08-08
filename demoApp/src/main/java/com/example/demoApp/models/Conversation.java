package com.example.demoApp.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "dashboard_conversation")
public class Conversation extends BaseModel implements Serializable {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Community community;

    @Column(name = "image")
    private String image;

    public Conversation() {
    }

    public Conversation(String name, String description, User currentUser, String filename, Community community) {
        this.title = name;
        this.description = description;
        this.user = currentUser;
        this.image = filename;
        this.community = community;

    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
