package com.example.websocket.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByStatus(Status status);

    Account findByNickNameAndFullName(String nickname, String fullName);

    Optional<Account> findByNickName(String nickName);
}
