package com.se3.example.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="node")
public class Node {
    @GeneratedValue(generator = "eid")
    @Id
    @Column(name="nid")
    private Integer nid;
    @Column(name="cid")
    private Integer cid;
    @Column(name="name")
    private String name;
//    @Column(name="size")
//    private Integer size;
//    @Column(name="color")
//    private  String color;
    @Column(name="classes")
    private  String classes;
    @Column(name="positionX")
    private  Double positionX;
    @Column(name="positionY")
    private  Double positionY;
}
