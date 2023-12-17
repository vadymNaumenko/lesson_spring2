package newsecurity.demo;

import lombok.RequiredArgsConstructor;
import newsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    private final UserService userService;

    @GetMapping("/activ/{cod}")
    public String getConfirmation(@PathVariable String cod) {

        return String.valueOf(userService.codIsValid(cod));
    }

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from demo controller");
    }

}
