package app.rest.service;

import app.rest.entity.Person;
import app.rest.repository.PersonRepository;
import app.rest.util.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional
    public void save (Person person){
        enrichPerson(person);
        personRepository.save(person);
    }

    public void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdateAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }
}
