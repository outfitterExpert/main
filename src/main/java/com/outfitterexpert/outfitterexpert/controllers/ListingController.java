package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.*;
import com.outfitterexpert.outfitterexpert.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ListingController {
    private final PropertyRepository propertyDao;
    private final ReviewRepository reviewDao;
    private final PackageRepository packageDao;
    private final AnimalRepository animalDao;


    public ListingController(PropertyRepository propDao, ReviewRepository reviewDao, PackageRepository packageDao, AnimalRepository animalDao) {
        this.propertyDao = propDao;
        this.reviewDao = reviewDao;
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
            model.addAttribute("package", new ListingPackage());
            return "listings/create";
        }
        return "redirect:/listings";
    }

    @PostMapping("/listings/create")
    public String submitListing(@ModelAttribute Property listing, @RequestParam(name="user-animal-list") String userAnimals){
        //get the user that's logged in
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listing.setUser(currentUser);

        //split up the animals the user has selected
        String[] animalList = userAnimals.split(",");
        //this list will be populated when an animal is found in the DB
        List<Animal> listingAnimals = new ArrayList<>();

        new Animal();
        Animal userAnimal;

        try {
            for (int i = 0; i < animalList.length; i++) {

                if(i == 0) {
                    userAnimal = animalDao.findByName(animalList[i]);
                }else{
                    userAnimal = animalDao.findByName(animalList[i].substring(1));
                }
                if(userAnimal != null){
                    System.out.println("Added:" + userAnimal.getName() + " with an id of: " + userAnimal.getId() + " to the Animal list");
                    listingAnimals.add(userAnimal);
                }else{
                    System.out.println("Could not be found in the database");
                }
            }
        }catch( NullPointerException npe){
            System.out.println("Animal not found");
        }

        //push the final list to the listing
        listing.setAnimals(listingAnimals);

        propertyDao.save(listing);

        return "redirect:/listings";
    }

    @GetMapping("/listings/{id}/package/create")
    public String createPackageForm(@PathVariable long id, Model model){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(currentUser != null){
                model.addAttribute("listPackage", new ListingPackage());
                model.addAttribute("id", id);
                return "listings/createPackage";
            }
            return "redirect:/listings/createPackage";
    }

    @PostMapping("/listings/{id}/package/create")
    public String createPackage(@PathVariable long id, @ModelAttribute ListingPackage listPackage, @RequestParam String date_exp) {

        System.out.println(id);
        Property property = propertyDao.findById(id);
        listPackage.setProperty(property);
        System.out.println(listPackage.getDate_exp());
        // fix this error parsing string to date
        try {
            Date postExpirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(date_exp);
            listPackage.setDate_exp(postExpirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        packageDao.save(listPackage);

        System.out.println(listPackage.getProperty_id());
        System.out.println(listPackage.getDescription());
        System.out.println(listPackage.getDuration());
        System.out.println(listPackage.isGuided());

        return "redirect:/listings";
    }

    @GetMapping("/listings/{id}")
    public String singleListing(@PathVariable long id, Model model){
        Property property = propertyDao.getById(id);
        List<Review> reviews = reviewDao.findByPropertyId(id);
        List<ListingPackage> listOfPackage = packageDao.findByPropertyId(id);

        boolean isPropertyOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isPropertyOwner = currentUser.getId() == property.getUser().getId();
        }
        model.addAttribute("listing", property);
        model.addAttribute("reviews", reviews);
        model.addAttribute("isPropertyOwner", isPropertyOwner);
        model.addAttribute("listOfPackage", listOfPackage);
        return "/listings/show";
    }

}
