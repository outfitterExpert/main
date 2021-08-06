package com.outfitterexpert.outfitterexpert.models;

import javax.persistence.*;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

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

    public Property (){
    }

    public Property(String username, String title, String location, int slots, boolean guided, boolean lodging, String method, User user) {
        this.username = username;
        this.title = title;
        this.location = location;
        this.slots = slots;
        this.guided = guided;
        this.lodging = lodging;
        this.method = method;
        this.user = user;
    }

    public Property(long id, String username, String title, String location, int slots, boolean guided, boolean lodging, String method, User user) {
        this.id = id;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
