package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.domain.User;
import spring.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // идем в базу данных и находим пользователя по его логину
        User user = userRepository.findUserByLogin(username);
        // собираем данные пользователя (его логин, его пароль, его роль)
        // TODO: еще надо сделать проверку, а что будет, если не нашли такого пользователя?
//        if (user == null) {
//            throw  new UsernameNotFoundException("User not found");
//        }

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getLogin())
                        .password(user.getPassword())
                        .roles(username.toUpperCase()) // user > USER, admin -> ADMIN
                        .build();
        // и возвращаем обратно
        return userDetails;
    }
}
