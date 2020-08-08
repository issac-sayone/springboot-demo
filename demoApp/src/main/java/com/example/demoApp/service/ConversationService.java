package com.example.demoApp.service;

import com.example.demoApp.dto.PageDTO;
import com.example.demoApp.models.Reply;
import com.example.demoApp.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConversationService {

    PageDTO getPagedConversations(int communityId, int page, int size);

    int addConversation(String title, String description, MultipartFile multipartFile, int communityId, User currentUser);

    int addReply(String text, User currentUser, int conversationId);

    List<Reply> getReplies(int conversationId);
}
