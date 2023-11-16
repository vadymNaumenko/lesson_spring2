package app.rest.service;

import app.rest.entity.Person;
import app.rest.repository.PersonRepository;
import app.rest.util.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findOne(int id){
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }
}
