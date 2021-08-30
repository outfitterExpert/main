package com.outfitterexpert.outfitterexpert.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String imgUrl;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int acres;

    @Column(nullable = false)
    private int slots;

    @Column(nullable = false)
    private boolean guided;

    @Column(nullable = false)
    private boolean lodging;

    @NotBlank
    @Column(nullable = false)
    private String method;

    //true is hunting - false is fishing
    @Column(nullable = false)
    private boolean type;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private List<ListingPackage> packages;

    @ManyToMany
    @JoinTable(
            name="post_animals",
            joinColumns = {@JoinColumn(name="property_id")},
            inverseJoinColumns = {@JoinColumn(name="animal_id")}
    )
    private List<Animal> animals;

    public Property (){
    }

    public Property(String title, String location, int acres, int slots, boolean guided, boolean lodging, String method, User user, List<Review> reviews, List<Booking> bookings, List<ListingPackage> packages, List<Animal> animals, String imgUrl, boolean type) {
        this.title = title;
        this.location = location;
        this.acres = acres;
        this.slots = slots;
        this.guided = guided;
        this.lodging = lodging;
        this.method = method;
        this.user = user;
        this.reviews = reviews;
        this.packages = packages;
        this.animals = animals;
        this.imgUrl = imgUrl;
        this.type = type;
    }

    public Property(String title, String location, int acres, int slots, boolean guided, boolean lodging, String method, User user, List<Animal> animals, boolean type) {
        this.title = title;
        this.location = location;
        this.acres = acres;
        this.slots = slots;
        this.guided = guided;
        this.lodging = lodging;
        this.method = method;
        this.user = user;
        this.animals = animals;
        this.type = type;
    }

    public Property(long id, String title, String location, int acres, int slots, boolean guided, boolean lodging, String method, User user, boolean type) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.acres = acres;
        this.slots = slots;
        this.guided = guided;
        this.lodging = lodging;
        this.method = method;
        this.user = user;
        this.type = type;
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

    public int getAcres() {
        return acres;
    }

    public void setAcres(int acres) {
        this.acres = acres;
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

    public List<ListingPackage> getPackages() {
        return packages;
    }

    public void setPackages(List<ListingPackage> packages) {
        this.packages = packages;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
