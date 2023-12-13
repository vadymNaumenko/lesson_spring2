package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.config.JwtUtil;
import spring.controller.dto.ErrorResponse;
import spring.controller.dto.LoginRequest;
import spring.controller.dto.LoginResponse;
import spring.domain.User;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginReq)  {
        try {
            // получаем объект Аутентификации
            // AuthenticationManager идет в базу данных, достает оттуда пользователя и потом проверяет его логин и пароль
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getLogin(), loginReq.getPassword()));

            // мы получили данные из базы, мы знаем, что все ок, следовательно, можем отдать пользователю токен
            // берем login
            String login = authentication.getName();
            // создаем такого же пользователя, но без пароля
            User user = new User(login, "");
            // создаем токен для этого пользователя
            String token = jwtUtil.createToken(user);
            // формируем ответ
            LoginResponse response = new LoginResponse(login, token);
            // отправляем клиенту JWT
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            // если неверный логин и пароль - возвращаем ошибку
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid login or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            // если какая-то другая ошибка - тоже возвращаем ошибку
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
