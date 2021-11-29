package com.se3.example.entity;



import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="edge")
public class Edge {
    @GeneratedValue(generator = "eid")
    @Id
    @Column(name="eid")
    private Integer eid;
    @Column(name="cid")
    private Integer cid;
    @Column(name="name")
    private String name;
    @Column(name="target")
    private String target;
    @Column(name="source")
    private String source;
    @Column(name="classes")
    private String classes;
}
