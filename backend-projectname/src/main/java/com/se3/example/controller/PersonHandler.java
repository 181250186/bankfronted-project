package com.se3.example.controller;

import com.se3.example.Repository.PersonRepository;
import com.se3.example.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonHandler {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/findAll")
    public List<Person> findAll(){
        return personRepository.findAll();

    }

}
