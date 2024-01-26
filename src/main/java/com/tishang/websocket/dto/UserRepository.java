package com.tishang.websocket.dto;

import com.tishang.websocket.Enum.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User ,String> {
    List<User> findAllByStatus(Status status);
}
