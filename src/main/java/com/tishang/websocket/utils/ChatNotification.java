package com.tishang.websocket.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatNotification {
    private String id;
    private String senderId ;
    private String recipientId;
    private String Content;
}
