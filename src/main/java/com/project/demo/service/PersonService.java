package com.project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.demo.model.AppError;
import com.project.demo.model.AppSuccess;
import com.project.demo.model.Person;
import com.project.demo.repository.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AppError appError;

    @Autowired
    private AppSuccess appSuccess;

    public ResponseEntity<?> save(Person person) {
        if(person.getName().equals("")) {
            this.appError.setMessage("Invalid name");
            return new ResponseEntity<>(appError, HttpStatus.BAD_REQUEST);
        }

        if(person.getAge() < 0) {
            this.appError.setMessage("Invalid age");
            return new ResponseEntity<>(appError, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.personRepository.save(person), HttpStatus.CREATED);
    } 
    
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.personRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findById(int id) {
        if(this.personRepository.countById(id) == 0) {
            this.appError.setMessage("Person not found");
            return new ResponseEntity<>(this.appError, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(this.personRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> update(Person person) {
        if(this.personRepository.countById(person.getId()) == 0) {
            this.appError.setMessage("Person not found");
            return new ResponseEntity<>(this.appError, HttpStatus.NOT_FOUND);
        }

        if(person.getName().equals("")) {
            this.appError.setMessage("Invalid name");
            return new ResponseEntity<>(this.appError, HttpStatus.BAD_REQUEST);
        }

        if(person.getAge() < 0) {
            this.appError.setMessage("Invalid age");
            return new ResponseEntity<>(this.appError, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.personRepository.save(person), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(int id) {
        if(this.personRepository.countById(id) == 0) {
            this.appError.setMessage("Person not found");
            return new ResponseEntity<>(this.appError, HttpStatus.NOT_FOUND);
        }

        Person person = this.personRepository.findById(id);
        this.personRepository.delete(person);
        this.appSuccess.setMessage("Person deleted!");
        return new ResponseEntity<>(this.appSuccess, HttpStatus.OK);
    }
}
