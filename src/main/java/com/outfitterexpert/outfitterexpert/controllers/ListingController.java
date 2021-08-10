package com.outfitterexpert.outfitterexpert.controllers;


import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListingController {
    private final PropertyRepository propertyDao;
    private final UserRepository userDao;


    public ListingController(PropertyRepository propDao, UserRepository userDao) {
        this.propertyDao = propDao;
        this.userDao = userDao;
    }


    @GetMapping("/listings")
    public String viewPosts(Model model) {
        model.addAttribute("listings", propertyDao.findAll());
        return "listings/index";
    }

    @GetMapping("/listings/create")
    public String createListing(Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser != null){
            model.addAttribute("listing", new Property());
            return "listings/create";
        }
        return "redirect:/listings";
    }

    @PostMapping("/listings/create")
    public String submitListing(@ModelAttribute Property property){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        property.setUser(currentUser);
        propertyDao.save(property);

        System.out.println(property.getTitle());
        System.out.println(property.getLocation());
        System.out.println(property.getSlots());
        System.out.println(property.isLodging());
        System.out.println(property.getUser().getId());

        return "redirect:/listings";
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
