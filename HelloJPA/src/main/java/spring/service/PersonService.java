package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.controller.dto.PersonDTO;
import spring.domin.Person;
import spring.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<PersonDTO> findAll() {

        return personRepository.findAll()
                .stream()
                .map(PersonDTO::getInstance)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null)
            return new PersonDTO(person.getId(), person.getUsername(), person.getCity());

        return null;
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public PersonDTO addPerson(PersonDTO person) {
        if (person != null) {
        return PersonDTO.getInstance(personRepository.save(new Person(person.getUsername(), person.getCity())));
        }

        return null;
    }

    public PersonDTO editePerson(PersonDTO person) {

        Person editePerson = personRepository.findById(person.getId()).orElse(null);
        if (editePerson != null) {
            editePerson.setUsername(person.getUsername());
            personRepository.save(editePerson);
            return person;
        }
        return null;
    }


}
