package com.outfitterexpert.outfitterexpert.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private boolean outfitter;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Property> properties;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings;


    public User() {
    }

    public User(String username, String password, String email, String firstName, String lastName, boolean outfitter) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.outfitter = outfitter;
    }

    public User(long id, String username, String password, String email, String firstName, String lastName, boolean outfitter) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.outfitter = outfitter;
    }

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isOutfitter() {
        return outfitter;
    }

    public void setOutfitter(boolean outfitter) {
        this.outfitter = outfitter;
    }


}
