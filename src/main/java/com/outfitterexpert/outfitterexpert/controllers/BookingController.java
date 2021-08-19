package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Booking;
import com.outfitterexpert.outfitterexpert.models.ListingPackage;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.BookingRepository;
import com.outfitterexpert.outfitterexpert.repositories.PackageRepository;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {
    private final UserRepository userDao;
    private final PackageRepository packageDao;
    private final PropertyRepository propertyDao;
    private final BookingRepository bookingDao;

    public BookingController(UserRepository userDao, PackageRepository packageDao, PropertyRepository propertyDao, BookingRepository bookingDao) {
        this.userDao = userDao;
        this.packageDao = packageDao;
        this.propertyDao = propertyDao;
        this.bookingDao = bookingDao;
    }

    @GetMapping("/listing/package/{id}/book")
    public String bookPackage(@PathVariable long id, Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser"){
            model.addAttribute("package", packageDao.findById(id));
            model.addAttribute("booking", new Booking());
            return "listings/bookListing";
        }else{
            return "redirect:/login";
        }
    }

    //create a post mapping that will handle the booking process
    @PostMapping("/listing/package/{id}/book")
    public String submitBooking(@PathVariable long id, @ModelAttribute Booking booking){
        //get parameters from the page
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("===============================================");
        System.out.println(id);
        System.out.println("===============================================");

        booking.setPackage(packageDao.findById(id));
        booking.setUser(currentUser);

        bookingDao.save(booking);
        //save booking
        //return user to their profile to view active bookings
        return "redirect:/listings";
    }
}
