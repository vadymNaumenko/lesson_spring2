package com.example.websocket.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public void saveUser(Account user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(Account user) {
        var storedUser = repository.findByNickName(user.getNickName()).orElse(null); // todo  mast be find by id
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<Account> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }

    public Account findByNicknameAndFullName(String nickname, String fullname) {
        return repository.findByNickNameAndFullName(nickname,fullname);
    }
}
