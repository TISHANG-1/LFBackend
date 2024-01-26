package com.tishang.websocket.dto.User;

import com.tishang.websocket.Enum.Status;
import com.tishang.websocket.dto.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);
}
