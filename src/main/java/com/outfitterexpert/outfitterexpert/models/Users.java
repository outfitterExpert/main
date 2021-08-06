package com.outfitterexpert.outfitterexpert.models;

public class Users {
    private long ID;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean outfitter;

    public Users(String username, String password, String email, String firstName, String lastName, boolean outfitter) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.outfitter = outfitter;
    }

    public Users(long ID, String username, String password, String email, String firstName, String lastName, boolean outfitter) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.outfitter = outfitter;
    }

    public Users() {

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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
