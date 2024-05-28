package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping
    public String hallo(){
        return "Halo";
    }
    @GetMapping("/vadym")
    public String halloVadym(){
        return "Halo Vadym";
    }
    @GetMapping("/hallo/vadym")
    public String halloVadymas(){
        return "Halo full";
    }
}
