package com.tishang.websocket.controller;

import com.tishang.websocket.Service.UserService;
import com.tishang.websocket.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    @MessageMapping("User/addUser")
    @SendTo("/user/topic")
    public User addUser(
            @Payload User user
    ){
        service.saveUser(user);
        return user ;
    }

    @MessageMapping("User/disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(
            @Payload User user
    ){
         service.disconnect(user);
         return user ;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
       return ResponseEntity.ok(service.findConnectedUser());
    }
}
