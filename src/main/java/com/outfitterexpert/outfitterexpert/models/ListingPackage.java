package com.outfitterexpert.outfitterexpert.models;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "package")
public class ListingPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double price;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean guided;

    @Column(nullable = false)
    private boolean lodging;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_exp;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listPackage")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name="property_id")
    private Property property;

    public ListingPackage() {
    }

    public ListingPackage(double price, int duration, String description, boolean guided, boolean lodging, Date date_exp, Property property) {
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.guided = guided;
        this.lodging = lodging;
        this.date_exp = date_exp;
        this.property = property;
    }

    public ListingPackage(long id, double price, int duration, String description, boolean guided, boolean lodging, Date date_exp, Property property) {
        this.id = id;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.guided = guided;
        this.lodging = lodging;
        this.date_exp = date_exp;
        this.property = property;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(Date date_exp) {
        this.date_exp = date_exp;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

}
