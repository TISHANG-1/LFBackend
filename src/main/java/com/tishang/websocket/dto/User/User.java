package com.tishang.websocket.dto.User;

import com.tishang.websocket.Enum.Status;
import lombok.Getter ;
import lombok.Setter ;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "user")
public class User {
       @Id
       private String nickName;
       private String fullName;
       private Status status;
}
