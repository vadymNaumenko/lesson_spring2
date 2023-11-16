package app.rest.controller;

import app.rest.controller.dto.PersonDTO;
import app.rest.entity.Person;
import app.rest.service.PersonService;
import app.rest.util.PersonErrorResponse;
import app.rest.util.PersonNotCreatedException;
import app.rest.util.PersonNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PersonService personService;
    private final ModelMapper modelMapper;


    @GetMapping("/all")
    public List<PersonDTO> getAll(){
        return personService.findAll().stream().map(this::convertToPersonDTO).toList();
    }

    @GetMapping("{id}")
    public PersonDTO getPerson(@PathVariable int id){
        return convertToPersonDTO(personService.findOne(id));
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasnt fond!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO person,
                                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder error = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for(FieldError f: fieldErrors){
                error.append(f.getField())
                        .append(" - ")
                        .append(f.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(error.toString());
        }
        personService.save(convertToPerson(person));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person,PersonDTO.class);
    }

    private Person convertToPerson(PersonDTO personDTO) {

        return modelMapper.map(personDTO,Person.class);
    }



}
