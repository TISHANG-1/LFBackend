package com.tishang.websocket.dto.Chat;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ChatMessage {
    private  String id ;
    private  String chatId;
    private  String senderId;
    private  String recipientId;
    private  String Content ;
    private Date timestamp;
}
