package com.example.websocket.controller;

import com.example.websocket.service.UserService;
import com.example.websocket.entity.User;
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

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(
            @Payload User user
    ){
        userService.saveUser(user);
        return user;
    }
    @MessageMapping("/user.disconnectedUser")
    @SendTo("/user/topic") // todo delete this annotation
    public User disconnect(@Payload User user){
        userService.disconnected(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUser(){
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
