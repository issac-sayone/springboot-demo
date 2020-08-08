package com.example.demoApp.service.impl;

import com.example.demoApp.dto.PageDTO;
import com.example.demoApp.models.Community;
import com.example.demoApp.models.Conversation;
import com.example.demoApp.models.Reply;
import com.example.demoApp.models.User;
import com.example.demoApp.repository.CommunityRepository;
import com.example.demoApp.repository.ConversationRepository;
import com.example.demoApp.repository.ReplyRepository;
import com.example.demoApp.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    ReplyRepository replyRepository;


    @Override
    public PageDTO getPagedConversations(int communityId, int page, int size) {
        Page<Conversation> conversations = conversationRepository.findByCommunityId(communityId, PageRequest.of(page, size));
        return new PageDTO(conversations);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File targetDir = new File("conversation-images");
        File convFile = new File(targetDir, file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @Override
    public int addConversation(String title, String description, MultipartFile multipartFile, int communityId, User currentUser) {

        try {
            File file = convertMultiPartToFile(multipartFile);
            String filename = multipartFile.getOriginalFilename();
            Optional<Community> community = communityRepository.findById(communityId);
            if (community.isPresent()) {
                Conversation conversation = new Conversation(title, description, currentUser, filename, community.get());
                conversationRepository.save(conversation);
                return 200;
            } else {
                return 206;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 424;
        }

    }

    @Override
    public int addReply(String text, User currentUser, int conversationId) {

        Optional<Conversation> conversation = conversationRepository.findById(conversationId);
        if (conversation.isPresent()) {
            Reply newReply = new Reply(text, currentUser, conversation.get());
            replyRepository.save(newReply);
            return 200;
        } else {
            return 424;
        }
    }

    @Override
    public List<Reply> getReplies(int conversationId) {
        List<Reply> replies = replyRepository.findByConversationId(conversationId);
        return replies;
    }

}
