package spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import spring.auth.AuthRequest;
import spring.auth.AuthResponse;
import spring.auth.AuthService;
import spring.auth.RegistrationRequest;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
        // todo if email not found make redirect and find by nickname
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registration(@RequestBody RegistrationRequest request){
        return ResponseEntity.ok(service.register(request));
    }
    @GetMapping("/hallo/vadym")
    public String halloVadymas(){
        return "Halo full";
    }
}
