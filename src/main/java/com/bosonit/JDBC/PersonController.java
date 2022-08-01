package com.bosonit.JDBC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    PersonRepository personRepository;
    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPerson(@RequestParam(required = false) String name) {
        try {
            List<Person> personList = new ArrayList<Person>();
            if (name == null)
                personRepository.findAll().forEach(personList::add);
            else
                personRepository.findByName(name).forEach(personList::add);
            if (personList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
        Person person = personRepository.findById(id);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/person")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        try {
            personRepository.save(new Person(person.getName(), person.getSurname(), false));
            return new ResponseEntity<>("Person was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/person/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Person _person = personRepository.findById(id);
        if (_person != null) {
            _person.setId(id);
            _person.setName(person.getName());
            _person.setSurname(person.getSurname());
            _person.setAdmin(person.isAdmin());
            personRepository.update(_person);
            return new ResponseEntity<>("Person was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Person with id=" + id, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable("id") long id) {
        try {
            int result = personRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Person with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Person was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete person.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/person")
    public ResponseEntity<String> deleteAllPerson() {
        try {
            int numRows = personRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Person(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorials.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/person/admin")
    public ResponseEntity<List<Person>> findByAdmin() {
        try {
            List<Person> personList = personRepository.findByAdmin(true);
            if (personList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(personList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
