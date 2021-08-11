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
    public String submitListing(@ModelAttribute Property listing){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userDao.findById(1L);

        listing.setUser(u);
        propertyDao.save(listing);

        System.out.println(listing.getTitle());
        System.out.println(listing.getLocation());
        System.out.println(listing.getSlots());
        System.out.println(listing.isLodging());
        System.out.println(listing.getUser().getId());

        return "redirect:/listings";
    }

    @GetMapping("/listings")
    public String viewPackages(Model model) {
        model.addAttribute("package", propertyDao.findAll());
        return "listings/packages";
    }
    @GetMapping("/listings/package/create")
    public String createPackage(Model model){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(currentUser != null){
                model.addAttribute("package", new Property());
                return "listings/package/create";
            }
            return "redirect:/listings/package";
    }

    @PostMapping("/listings/package/create")
    public String submitPackage(@ModelAttribute Property p){
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userDao.findById(1L);

        p.setUser(u);
        propertyDao.save(p);

        System.out.println(p.getProperty_id());
        System.out.println(p.getDescription());
        System.out.println(p.getDuration());
        System.out.println(p.isGuided());
        System.out.println(p.getUser().getId());

        return "redirect:/listings/package";


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
