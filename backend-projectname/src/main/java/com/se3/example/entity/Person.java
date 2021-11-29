package com.se3.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Person {
    @Id
    private Integer pid;
    private String name;
    private String biography;
    private  String birthplace;

}
