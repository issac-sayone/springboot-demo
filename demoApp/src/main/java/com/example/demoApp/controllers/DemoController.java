package com.example.demoApp.controllers;

import com.example.demoApp.dto.PageDTO;
import com.example.demoApp.models.Reply;
import com.example.demoApp.models.User;
import com.example.demoApp.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class DemoController {

    private final AuthenticationManager authenticationManager;
    @Autowired
    ConversationService conversationService;

    public DemoController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping(value = "getReplies/{conversationId}")
    public ResponseEntity getRepliesList(@PathVariable int conversationId) {
        List<Reply> replies = conversationService.getReplies(conversationId);
        return new ResponseEntity(replies, HttpStatus.OK);
    }

    @PostMapping(value = "/add-reply/{conversationId}")
    public ResponseEntity addReply(@PathVariable int conversationId,
                                   @RequestParam(name = "text") String text) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        int status = conversationService.addReply(text, currentUser, conversationId);

        return new ResponseEntity(HttpStatus.valueOf(status));
    }

    //Sample API to get paginated conversations list
    //sending all data now
    //response data can be customsed for the front-end by having a custom DTO - eg: conversationDTO
    @GetMapping(value = "{communityId}/get-conversations")
    public ResponseEntity getConversations(@PathVariable int communityId,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        PageDTO conversationsPage = conversationService.getPagedConversations(communityId, page, size);
        return new ResponseEntity(conversationsPage, HttpStatus.OK);

    }

    //Sample API to create a conversation by a logged in user
    //file is stored in the file system(or any 3rd party) and the image url is stored in the DB
    @PostMapping(value = "/create-conversation")
    public ResponseEntity createConversation(@RequestParam("title") String title,
                                             @RequestParam("description") String description,
                                             @RequestPart(value = "file") MultipartFile multipartFile,
                                             @RequestParam("community_id") int communityId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        int status = conversationService.addConversation(title, description, multipartFile, communityId, currentUser);
        return new ResponseEntity(HttpStatus.valueOf(status));

    }


    // Sample API to log the user in
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestParam("username") final String username,
                                @RequestParam("password") final String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
