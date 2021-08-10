package com.outfitterexpert.outfitterexpert.controllers;


import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListingController {
    private final PropertyRepository propDao;
    private final UserRepository userDao;


    public ListingController(PropertyRepository propDao, UserRepository userDao) {
        this.propDao = propDao;
        this.userDao = userDao;
    }


    @GetMapping("/listings")
    public String viewPosts(Model model) {
        model.addAttribute("listings", propDao.findAll());
        return "listings/index";
    }

    @GetMapping("/listing/create")
    public String createListing(Model model){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(currentUser != null){
//            model.addAttribute("listing", new Property());
//            return "listings/create";
//        }
//        return "redirect:/listings";
        model.addAttribute("listing", new Property());
        return "listings/create";
    }

//    @PostMapping("/listing/create")
//    public String postNewListing(){
//
//    }
//
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
