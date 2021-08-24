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

    @Column
    private String user_location;

    @Column
    private String img_user;

    @Column
    private String bio;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Property> properties;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="bookmarks",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")}
    )
    private List<Property> bookmarks;


    public User() {
    }
    public User(User copy) {
        id = copy.id;
        username = copy.username;
        password = copy.password;
        email = copy.email;
        firstName = copy.firstName;
        lastName = copy.lastName;
        outfitter = copy.outfitter;
        properties = copy.properties;
        reviews = copy.reviews;
        bookings = copy.bookings;
        bookmarks = copy.bookmarks;
        user_location = copy.user_location;
        img_user = copy.img_user;
        bio = copy.bio;

//        review = copy.reviews;
    }



    public User(long id, String username, String password, String email, String firstName, String lastName, boolean outfitter, List<Property> properties, List<Review> reviews, List<Booking> bookings, List<Property> bookmarks, String user_location, String img_user, String bio) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.outfitter = outfitter;
        this.properties = properties;
        this.reviews = reviews;
        this.bookings = bookings;
        this.bookmarks = bookmarks;
        this.user_location = user_location;
        this.img_user = img_user;
        this.bio = bio;
    }

    public User(String username, String password, String email, String firstName, String lastName, boolean outfitter, String user_location, String bio) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.outfitter = outfitter;
        this.user_location = user_location;
        this.bio = bio;
    }


    public User(long id, String username, String password, String email, String firstName, String lastName, boolean outfitter, String user_location, String bio) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.outfitter = outfitter;
        this.user_location = user_location;
        this.bio = bio;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<Property> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Property> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public String getUser_location() {
        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public String getImg_user() {
        return img_user;
    }

    public void setImg_user(String img_user) {
        this.img_user = img_user;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


}


