package com.tishang.websocket.Service;

import com.tishang.websocket.dto.ChatRoom.ChatRoom;
import com.tishang.websocket.dto.ChatRoom.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    public Optional<String>  getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::getChatId).or(()->{
             if(createNewRoomIfNotExists){
                 var  chatId = "" ;
                  if(recipientId.equals("commonroom@tishang.com")){
                       chatId = recipientId ;
                  }
                  else   chatId =createChatId(senderId , recipientId) ;
                  return Optional.of(chatId) ;
             }
             return Optional.empty();
        });
    }

    private String createChatId(String senderId , String recipientId){
       var chatId = String.format("%s_%s" , senderId , recipientId) ;
       ChatRoom SenderRecipient = ChatRoom.builder()
               .chatId(chatId)
               .senderId(senderId)
               .recipientId(recipientId)
               .build();
        ChatRoom RecipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        chatRoomRepository.save(SenderRecipient);
        chatRoomRepository.save(RecipientSender) ;
       return null;
    }
}
