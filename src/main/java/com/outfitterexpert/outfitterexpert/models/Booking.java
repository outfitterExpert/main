package com.outfitterexpert.outfitterexpert.models;


import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_end;

    public Booking() {
    }

    public Booking(long id, User user, ListingPackage listPackage, Date date_start, Date date_end) {
        this.id = id;
        this.user = user;
        this.listPackage = listPackage;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public Booking(User user, ListingPackage listPackage, Date date_start, Date date_end) {
        this.user = user;
        this.listPackage = listPackage;
        this.date_start = date_start;
        this.date_end = date_end;
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

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }
}
