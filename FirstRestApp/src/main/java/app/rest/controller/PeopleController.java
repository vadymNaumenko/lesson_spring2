package app.rest.controller;

import app.rest.entity.Person;
import app.rest.service.PersonService;
import app.rest.util.PersonErrorResponse;
import app.rest.util.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Person person = personService.findOne(id);
        System.out.println(person);
        return person;
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasnt fond!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
