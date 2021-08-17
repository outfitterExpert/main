package com.outfitterexpert.outfitterexpert.models;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private ListingPackage listPackage;

    @Column(nullable = false)
    private Date start_date;

    @Column(nullable = false)
    private Date end_date;

    public Booking() {
    }

    public Booking(long id, User user, ListingPackage listPackage, Date start_date, Date end_date) {
        this.id = id;
        this.user = user;
        this.listPackage = listPackage;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Booking(User user, ListingPackage listPackage, Date start_date, Date end_date) {
        this.user = user;
        this.listPackage = listPackage;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ListingPackage getPackage() {
        return listPackage;
    }

    public void setPackage(ListingPackage listPackage) {
        this.listPackage = listPackage;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
