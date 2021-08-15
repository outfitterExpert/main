package com.outfitterexpert.outfitterexpert.controllers;


import com.outfitterexpert.outfitterexpert.models.Animal;
import com.outfitterexpert.outfitterexpert.models.ListingPackage;
import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.AnimalRepository;
import com.outfitterexpert.outfitterexpert.repositories.PackageRepository;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ListingController {
    private final PropertyRepository propertyDao;
    private final UserRepository userDao;
    private final PackageRepository packageDao;
    private final AnimalRepository animalDao;


    public ListingController(PropertyRepository propDao, UserRepository userDao, PackageRepository packageDao, AnimalRepository animalDao) {
        this.propertyDao = propDao;
        this.userDao = userDao;
        this.packageDao = packageDao;
        this.animalDao = animalDao;
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
    public String submitListing(@ModelAttribute Property listing, @RequestParam(name="user-animal-list") String userAnimals){
        //get the user that's logged in
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listing.setUser(currentUser);

        //figure out the next post id so we can save the animals to the id that will be next in line (the new --yet to be saved-- post id)
        Property findLastProp = propertyDao.findTopByOrderByIdDesc();
        long newPostId = findLastProp.getId() + 1;

        //split up the animals the user has selected
        String[] animalList = userAnimals.split(",");

        System.out.println("===================================");
        System.out.println(newPostId);

        new Animal();
        Animal userAnimal;

        try {
            for (int i = 0; i < animalList.length; i++) {


                if(i == 0) {
                    userAnimal = animalDao.findByName(animalList[i]);
                }else{
                    userAnimal = animalDao.findByName(animalList[i].substring(1));
                }


                System.out.println("user entered: ");
                System.out.println(animalList[i]);

                if(userAnimal != null){
                    System.out.println("Database found: ");
                    System.out.println(userAnimal.getId());
                    System.out.println(userAnimal.getName());
                }else{
                    System.out.println("Could not be found in the database");
                }

            }
        }catch( NullPointerException npe){
            System.out.println("Animal not found");
        }
        System.out.println("===================================");

        //save the animals to the post-animal table
        for(String animal : animalList){
            //how can we save(insert into the post_animal DB) without a repository
        }

        //save the final product
        propertyDao.save(listing);
        return "redirect:/listings";
    }

    @GetMapping("/listings/package/create")
    public String createPackage(Model model){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(currentUser != null){
                model.addAttribute("listPackage", new ListingPackage());
                return "listings/createPackage";
            }
            return "redirect:/listings/index";
    }

    @PostMapping("/listings/package/create")
    public String submitPackage(@ModelAttribute ListingPackage listPackage) {
        Property property = propertyDao.findById(1L);
        listPackage.setProperty(property);
        packageDao.save(listPackage);

        System.out.println(listPackage.getProperty_id());
        System.out.println(listPackage.getDescription());
        System.out.println(listPackage.getDuration());
        System.out.println(listPackage.isGuided());

        return "redirect:/listings/package";
    }

//    @PostMapping("/listing/create")
//    public String postNewListing(){
//

    @GetMapping("/listings/{id}")
    public String singleListing(@PathVariable long id, Model model){
        Property property = propertyDao.getById(id);
        boolean isPropertyOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isPropertyOwner = currentUser.getId() == property.getUser().getId();
        }
        model.addAttribute("listing", property);
        model.addAttribute("isPropertyOwner", isPropertyOwner);
        return "/listings/show";
    }


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
