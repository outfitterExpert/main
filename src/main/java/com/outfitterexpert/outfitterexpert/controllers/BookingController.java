package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Booking;
import com.outfitterexpert.outfitterexpert.repositories.PackageRepository;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookingController {
    private final UserRepository userDao;
    private final PackageRepository packageDao;
    private final PropertyRepository propertyDao;

    public BookingController(UserRepository userDao, PackageRepository packageDao, PropertyRepository propertyDao) {
        this.userDao = userDao;
        this.packageDao = packageDao;
        this.propertyDao = propertyDao;
    }

    @GetMapping("/listing/package/{id}/book")
    public String bookPackage(@PathVariable long id, Model model){

        model.addAttribute("id", id); //package id
        model.addAttribute("booking", new Booking());

        return "listings/bookListing";
    }

    //create a post mapping that will handle the booking process
}
