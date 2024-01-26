package com.tishang.websocket.dto.ChatRoom;

import com.tishang.websocket.dto.ChatRoom.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom,String > {
   Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId , String recipientId);

}
