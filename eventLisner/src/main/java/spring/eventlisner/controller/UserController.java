package spring.eventlisner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.eventlisner.entity.User;
import spring.eventlisner.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    @PostMapping
    public User create(String name){
        return userService.create(name);
    }
    @PutMapping
    public User update(int id,@RequestBody User user){
        return userService.update(id,user);
    }
    @GetMapping
    public User findById(int id){
       return userService.findById(id);
    }


}
