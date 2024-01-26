package com.tishang.websocket.dto;

import com.tishang.websocket.Enum.Status;
import lombok.Getter ;
import lombok.Setter ;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class User {
       @Id
       private String NickName;
       private String FullName;
       private Status status;
}
