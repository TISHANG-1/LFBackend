package com.tishang.websocket.controller;

import com.tishang.websocket.Service.ChatMessageService;
import com.tishang.websocket.dto.Chat.ChatMessage;
import com.tishang.websocket.utils.ChatNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", maxAge = 3600)
public class ChatController {
   private final SimpMessagingTemplate messagingTemplate ;
   private final ChatMessageService chatMessageService ;
   @GetMapping("/messages/{senderId}/{recipientId}")
   public ResponseEntity<List<ChatMessage>> findChatMessages(
           @PathVariable ("senderId") String senderId,
           @PathVariable("recipientId") String recipientId
   ) {
       return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
   }

    @GetMapping("/messages/{senderId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable ("senderId") String senderId

    ) {
        return ResponseEntity.ok(chatMessageService.findCommonMessage(senderId));
    }
   @MessageMapping("/chat")
   public void processMessage(@Payload ChatMessage chatMessage){
       // here we will have queue to which a user can subscribe to
       // bot will be subscribing to the bot/queue/messages
       ChatMessage savedMessage  = chatMessageService.save(chatMessage);
       messagingTemplate.convertAndSendToUser(
              chatMessage.getRecipientId(), "/queue/messages", ChatNotification.builder()
                       .id(savedMessage.getId())
                       .senderId(savedMessage.getSenderId())
                       .recipientId(savedMessage.getRecipientId())
                       .Content(savedMessage.getContent())
                       .build()
       );

   }
}
