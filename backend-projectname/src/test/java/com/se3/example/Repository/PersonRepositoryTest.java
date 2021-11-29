package com.se3.example.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {

    @Autowired
    private  PersonRepository personRepository;

    @Test
    void findAll(){
        System.out.println();
    }
}