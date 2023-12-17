package newsecurity.service;

import lombok.RequiredArgsConstructor;
import newsecurity.model.ConfirmationCode;
import newsecurity.model.State;
import newsecurity.model.User;
import newsecurity.repository.ConfirmationCodeRepository;
import newsecurity.repository.UserRepository;
import org.hibernate.engine.spi.Status;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    @Transactional
    public boolean codIsValid(String cod) {
        User user = userRepository.findFirstByCodes_Code(cod).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setState(State.CONFIRMED);
        userRepository.save(user);
        return true;
    }
//    private final MailSender mailSender;


}
