package app.rest.controller;

import app.rest.entity.Person;
import app.rest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PersonService personService;

    @GetMapping("/all")
    public List<Person> getAll(){
        return personService.findAll();
    }
    @GetMapping("{id}")
    public Person getPerson(@PathVariable int id){
        return personService.findOne(id);
    }

}
