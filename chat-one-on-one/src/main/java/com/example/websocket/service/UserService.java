package com.example.websocket.service;

import com.example.websocket.repository.UserRepository;
import com.example.websocket.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void saveUser(User user){
        user.setStatus(User.Status.ONLINE);
        userRepository.save(user);
    }

    public void disconnected(User user){
        User storedUser = userRepository.findById(user.getId()).orElse(null);
        if (storedUser != null){
            storedUser.setStatus(User.Status.OFFLINE);
            userRepository.save(storedUser);
        }

    }

    public List<User> findConnectedUsers(){
        return userRepository.findAllByStatus(User.Status.ONLINE);
    }
}
