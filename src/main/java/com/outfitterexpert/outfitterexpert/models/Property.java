package com.outfitterexpert.outfitterexpert.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int slots;

    @Column(nullable = false)
    private boolean guided;

    @Column(nullable = false)
    private boolean lodging;

    @Column(nullable = false)
    private String method;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private List<Booking> bookings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private List<ListingPackage> packages;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="post_animals",
            joinColumns = {@JoinColumn(name="animal_id")},
            inverseJoinColumns = {@JoinColumn(name="property_id")}
    )
    private List<Animal> animals;

    public Property (){
    }

    public Property(String title, String location, int slots, boolean guided, boolean lodging, String method, User user, List<Review> reviews, List<Booking> bookings, List<ListingPackage> packages, List<Animal> animals) {
        this.title = title;
        this.location = location;
        this.slots = slots;
        this.guided = guided;
        this.lodging = lodging;
        this.method = method;
        this.user = user;
        this.reviews = reviews;
        this.packages = packages;
        this.animals = animals;
    }

    public Property(String title, String location, int slots, boolean guided, boolean lodging, String method, User user) {
        this.title = title;
        this.location = location;
        this.slots = slots;
        this.guided = guided;
        this.lodging = lodging;
        this.method = method;
        this.user = user;
    }

    public Property(long id, String title, String location, int slots, boolean guided, boolean lodging, String method, User user) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.slots = slots;
        this.guided = guided;
        this.lodging = lodging;
        this.method = method;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public boolean isGuided() {
        return guided;
    }

    public void setGuided(boolean guided) {
        this.guided = guided;
    }

    public boolean isLodging() {
        return lodging;
    }

    public void setLodging(boolean lodging) {
        this.lodging = lodging;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<ListingPackage> getPackages() {
        return packages;
    }

    public void setPackages(List<ListingPackage> packages) {
        this.packages = packages;
    }

//    public List<Property> getProperties() {
//        return properties;
//    }
//
//    public void setProperties(List<Property> properties) {
//        this.properties = properties;
//    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
}
