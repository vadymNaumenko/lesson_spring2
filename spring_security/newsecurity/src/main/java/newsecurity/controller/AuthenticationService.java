package newsecurity.controller;

import lombok.RequiredArgsConstructor;
import newsecurity.config.JwtService;
import newsecurity.mail.TemplateMailSender;
import newsecurity.model.ConfirmationCode;
import newsecurity.model.Role;
import newsecurity.model.State;
import newsecurity.model.User;
import newsecurity.repository.ConfirmationCodeRepository;
import newsecurity.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final TemplateMailSender templateMailSender;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .state(State.NOT_CONFIRM)
                .build();
        userRepository.save(user);
// mail sender
        String codValid = UUID.randomUUID().toString();
        ConfirmationCode code = ConfirmationCode.builder()
                .code(codValid)
                .user(user)
                .expiredDateTime(LocalDateTime.now().plusMinutes(15))
                .build();

        confirmationCodeRepository.save(code);
        templateMailSender.sendMail(user.getUsername(), "Registration","http://localhost:8080/"+ codValid); //todo mast be add baseUrl/+codValid
// end
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
