package com.outfitterexpert.outfitterexpert.controllers;


import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListingController {
    private final PropertyRepository propDao;
    private final UserRepository userDao;


    public ListingController(PropertyRepository propDao, UserRepository userDao) {
        this.propDao = propDao;
        this.userDao = userDao;
    }




//    @GetMapping("/listings")
//    // listing/index
//
//    @GetMapping("/listings/{id}")
//    // listings/show
//
//    @GetMapping("/listings/{id}/edit")
//    // listings/edit
//
//    @GetMapping("/listings/create")
//    // listings/create
//
//    @GetMapping("/listings/delete/{id}")
//    // redirect:/listings

}
