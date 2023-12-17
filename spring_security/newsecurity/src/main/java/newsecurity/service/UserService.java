package newsecurity.service;

import lombok.RequiredArgsConstructor;
import newsecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public boolean codIsValid(String cod) {
        return false;
    }
//    private final MailSender mailSender;


}
