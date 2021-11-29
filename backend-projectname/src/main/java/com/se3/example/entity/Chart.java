package com.se3.example.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="chart")
public class Chart {
    @GeneratedValue(generator = "cid")
    @Id
    @Column(name="cid")
    private Integer cid;
    @Column(name="name")
    private String name;


}