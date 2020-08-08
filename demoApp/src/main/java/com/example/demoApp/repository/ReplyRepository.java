package com.example.demoApp.repository;

import com.example.demoApp.models.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    List<Reply> findByConversationId(int conversationId);
}
