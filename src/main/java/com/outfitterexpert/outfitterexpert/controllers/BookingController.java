package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Booking;
import com.outfitterexpert.outfitterexpert.models.ListingPackage;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.PackageRepository;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/listing/package/book")
    public String bookPackage(@RequestParam long package_id, Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser != null){
            model.addAttribute("package", packageDao.findById(package_id));
            return "listings/bookListing";
        }
        return "redirect:/login";

    }

    //create a post mapping that will handle the booking process
    @PostMapping("/listing/package/book")
    public String submitBooking(){
        //get parameters from the page
        //save booking
        //return user to their profile to view active bookings
        return "redirect:/listings";
    }
}
