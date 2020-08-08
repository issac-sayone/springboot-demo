package com.example.demoApp.dto;

import com.example.demoApp.models.Conversation;


// Sample DTO class to send custom response data to the front-end
public class conversationDTO {

    private int id;
    private String title;
    private String description;
    private String user;

    public conversationDTO(Conversation conversation) {
        this.id = conversation.getId();
        this.title = conversation.getTitle();
        this.description = conversation.getDescription();
        this.user = conversation.getUser().getUsername();
    }
}
