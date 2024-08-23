package com.project.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.model.Person;
import com.project.demo.repository.PersonRepository;
import com.project.demo.service.PersonService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public ResponseEntity<?> save(@RequestBody Person person) {
        return this.personService.save(person);
    } 
    
    @GetMapping("/person")
    public ResponseEntity<?> findAll() {
        return this.personService.findAll();
    }
    
    @GetMapping("/person/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return this.personService.findById(id);
    }

    @GetMapping("/person/count")
    public long countPerson() {
        return this.personRepository.count();
    }

    @GetMapping("/person/order-by-name/asc")
    public List<Person> orderByNameAsc() {
        return this.personRepository.findByOrderByName();
    }

    @GetMapping("/person/order-by-name/desc")
    public List<Person> orderByNameDesc() {
        return this.personRepository.findByOrderByNameDesc();
    }

    @GetMapping("/person/order-by-age/{name}")
    public List<Person> findByNameOrderAge(@PathVariable String name) {
        return this.personRepository.findByNameOrderByAgeDesc(name);
    }

    @GetMapping("/person/search-name/contain/{str}")
    public List<Person> findByNameContaining(@PathVariable String str) {
        return this.personRepository.findByNameContaining(str);
    }

    @GetMapping("/person/search-name/start-with/{str}")
    public List<Person> findByNameStartsWith(@PathVariable String str) {
        return this.personRepository.findByNameStartsWith(str);
    }

    @GetMapping("/person/search-name/end-with/{str}")
    public List<Person> findByNameEndsWith(@PathVariable String str) {
        return this.personRepository.findByNameEndsWith(str);
    }

    @GetMapping("/person/sum/age")
    public int sumAges() {
        return this.personRepository.sumAges();
    }

    @GetMapping("/person/age/great-or-equal/{number}")
    public List<Person> findWhereAgeGeatOrEqualThan(@PathVariable int number) {
        return this.personRepository.findWhereAgeGreatOrEqaulThan(number);
    }

    @PutMapping("/person")
    public ResponseEntity<?> update(@RequestBody Person person) {
        return this.personService.update(person);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return this.personService.delete(id);
    }
}
