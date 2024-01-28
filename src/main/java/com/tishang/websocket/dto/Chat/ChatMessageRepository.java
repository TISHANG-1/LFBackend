package com.tishang.websocket.dto.Chat;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
     List<ChatMessage> findByChatId(String S);
     List<ChatMessage> findAllById(String S);
}
