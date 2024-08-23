package com.project.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.demo.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer>{
    List<Person> findAll();

    Person findById(int id);

    List<Person> findByOrderByName();

    List<Person> findByOrderByNameDesc();

    List<Person> findByNameOrderByAgeDesc(String name);

    List<Person> findByNameContaining(String str);

    List<Person> findByNameStartsWith(String str);

    List<Person> findByNameEndsWith(String str);

    @Query(value = "SELECT SUM(age) FROM person", nativeQuery = true)
    int sumAges();

    @Query(value = "SELECT * FROM person WHERE age >= :number", nativeQuery = true)
    List<Person> findWhereAgeGreatOrEqaulThan(int number);

    int countById(int Id);
}
