package com.example.demoApp.repository;

import com.example.demoApp.models.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

    //Any query goes here
    Page<Conversation> findByCommunityId(int communityId, Pageable page);


}
