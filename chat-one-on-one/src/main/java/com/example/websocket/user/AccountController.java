package com.example.websocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @MessageMapping("/user.addUser")
//    @SendTo("/user/topic")  // todo public
    public void addUser(
            @Payload Account user
    ) {
        accountService.saveUser(user);
//        return user;
    }

    @MessageMapping("/user.disconnectUser")
//    @SendTo("/user/topic")
    public void disconnectUser(
            @Payload Account user
    ) {
        accountService.disconnect(user);
//        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Account>> findConnectedUsers() {
        return ResponseEntity.ok(accountService.findConnectedUsers());
    }

    @GetMapping("/user/{nickname}/{fullname}")
    public Account findUser(@PathVariable String nickname, @PathVariable String fullname) {
        Account res = accountService.findByNicknameAndFullName(nickname, fullname);
        return res;
    }
}
