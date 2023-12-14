package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.config.JwtUtil;
import spring.controller.dto.ErrorResponce;
import spring.controller.dto.LoginReq;
import spring.controller.dto.LoginResponce;
import spring.domain.User;

import java.util.Collection;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginReq request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
            String login = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            User user = new User(login, authorities);
            String token = jwtUtil.createToken(user);
            LoginResponce responce = new LoginResponce(login, token);

            return ResponseEntity.ok(responce);
        } catch (BadCredentialsException e) {
            ErrorResponce error = new ErrorResponce(HttpStatus.BAD_REQUEST, "Invalid login or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            ErrorResponce error = new ErrorResponce(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
