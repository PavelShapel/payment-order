package com.pavelshapel.aws.ec2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Greeting {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
