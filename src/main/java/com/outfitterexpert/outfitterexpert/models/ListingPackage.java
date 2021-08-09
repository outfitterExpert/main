package com.outfitterexpert.outfitterexpert.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "package")
public class ListingPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, insertable = false, updatable = false)
    private long property_id;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private Date post_exp;

    @ManyToOne
    @JoinColumn(name="property_id")
    private Property property;

    public ListingPackage() {
    }

    public ListingPackage(long property_id, double price, int duration, Date post_exp, Property property) {
        this.property_id = property_id;
        this.price = price;
        this.duration = duration;
        this.post_exp = post_exp;
        this.property = property;
    }

    public ListingPackage(long id, long property_id, double price, int duration, Date post_exp, Property property) {
        this.id = id;
        this.property_id = property_id;
        this.price = price;
        this.duration = duration;
        this.post_exp = post_exp;
        this.property = property;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProperty_id() {
        return property_id;
    }

    public void setProperty_id(long property_id) {
        this.property_id = property_id;
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

    public Date getPost_exp() {
        return post_exp;
    }

    public void setPost_exp(Date post_exp) {
        this.post_exp = post_exp;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
