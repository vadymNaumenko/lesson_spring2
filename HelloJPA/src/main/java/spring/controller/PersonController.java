package spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.controller.dto.PersonDTO;
import spring.service.PersonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/all")
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id){
        return personService.findById(id);
    }

    @PostMapping("/add")
    public PersonDTO addNewPerson(PersonDTO personDTO){
        return personService.addPerson(personDTO);
    }

    @PutMapping("/update")
    public PersonDTO editePerson(PersonDTO personDTO){
        return personService.editePerson(personDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        personService.deleteById(id);
    }

}
