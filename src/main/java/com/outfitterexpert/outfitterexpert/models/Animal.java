package com.outfitterexpert.outfitterexpert.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "animals")
    private List<Property> properties;


    public Animal() {
    }

    public Animal(String name, List<Property> properties) {
        this.name = name;
        this.properties = properties;
    }

    public Animal(long id, String name, List<Property> properties) {
        this.id = id;
        this.name = name;
        this.properties = properties;
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

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
