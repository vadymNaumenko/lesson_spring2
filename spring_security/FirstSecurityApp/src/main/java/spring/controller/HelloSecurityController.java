package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import spring.entity.Person;
import spring.security.PersonDetails;
import spring.service.AdminService;

@Controller
public class HelloSecurityController {
@Autowired
    private AdminService adminService;
    @GetMapping("/hello")
    public String helloSecurity(){
        return "./auth/hello";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "./auth/admin";
    }

    @GetMapping("/showUser")
    public Person showUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }

    @GetMapping("/admin")
    public String admin(){

        adminService.doAdminStaff();

        return "admin";
    }
}
