package com.outfitterexpert.outfitterexpert.models;

import javax.persistence.*;

@Entity
@Table(name = "animals")

public class Animals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;


    public Animals() {
    }

    public Animals(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animals(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
