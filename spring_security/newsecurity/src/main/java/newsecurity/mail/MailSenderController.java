package newsecurity.mail;

import lombok.RequiredArgsConstructor;
import newsecurity.repository.ConfirmationCodeRepository;
import newsecurity.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailSenderController {

    private final UserService userService;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    @GetMapping("/cod/{cod}")
    public String checkCod(@PathVariable String cod){
        confirmationCodeRepository.findByCode(cod);
        boolean b = userService.codIsValid(cod);
       return "ok";
    }

}
