package com.outfitterexpert.outfitterexpert.controllers;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.outfitterexpert.outfitterexpert.models.*;
import com.outfitterexpert.outfitterexpert.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String viewPosts(@CookieValue(value = "type", defaultValue = "true") String strType, Model model) {
        boolean type = Boolean.parseBoolean(strType);

        List<Property> retrievedProperties = propertyDao.findAll();
        List<Property> sendToIndex = new ArrayList<>();
        if(type){
            for(Property p : retrievedProperties){
                if(p.isType()){ //This populates an array for hunting trips
                    sendToIndex.add(p);
                }
            }
        }else{
            for(Property p : retrievedProperties){
                if(!p.isType()){ //This populates an array for fishing trips
                    sendToIndex.add(p);
                }
            }
        }
        model.addAttribute("listings", sendToIndex);
        return "listings/index";
    }


    @GetMapping("/listings/hunting")
    public String viewHuntingPosts(Model model) {
        Cookie typeCookie = new Cookie("type", "true");
        List<Property> retrievedProperties = propertyDao.findAll();
        List<Property> sendToIndex = new ArrayList<>();
        for(Property p : retrievedProperties){
            if(p.isType()){ //This populates an array for hunting trips
                sendToIndex.add(p);
            }
        }
        model.addAttribute("listings", sendToIndex);
        return "listings/index";
    }

    @GetMapping("/listings/fishing")
    public String viewFishingPosts(Model model) {
        Cookie typeCookie = new Cookie("type", "false");
        List<Property> retrievedProperties = propertyDao.findAll();
        List<Property> sendToIndex = new ArrayList<>();

        for(Property p : retrievedProperties){
            if(!p.isType()){ //This populates an array for fishing trips
                sendToIndex.add(p);
            }
        }
        model.addAttribute("listings", sendToIndex);
        return "listings/index";
    }

    //This is for hunting listings
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

    //This is for fishing listings
    @GetMapping("/listings/create-fish")
    public String createFishListing(Model model){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(currentUser != null){
            model.addAttribute("listing", new Property());
            model.addAttribute("package", new ListingPackage());
            return "listings/create-fish";
        }
        return "redirect:/listings";
    }

    @PostMapping("/listings/create")
    public String submitListing(@ModelAttribute Property listing, @RequestParam(name="user-animal-list") String userAnimals){
        //get the user that's logged in
        System.out.println(listing.getImgUrl());
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

        if(listing.getImgUrl().equals("")){
            listing.setImgUrl("https://cdn.filestackcontent.com/Ktwfy5keSHaze3YmzsiJ");
        }

        propertyDao.save(listing);
        return "redirect:/listings";
    }

    @GetMapping("/listings/{id}/package/create")
    public String createPackageForm(@PathVariable long id, Model model){
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(currentUser != null){
                model.addAttribute("listPackage", new ListingPackage());
                model.addAttribute("listing_id", id);
                return "listings/createPackage";
            }
            return "redirect:/listings/createPackage";
    }

    @PostMapping("/listings/{listing_id}/package/create")
    public String createPackage(@PathVariable long listing_id, @ModelAttribute ListingPackage listPackage, @RequestParam String date_exp) {

        System.out.println(listing_id);
        Property property = propertyDao.findById(listing_id);
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

    @GetMapping("/listings/{id}/edit")
    public String editListingForm(@PathVariable long id, Model model ){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property property = propertyDao.getById(id);
        if(currentUser.getId() == property.getUser().getId()) {
            model.addAttribute("listing", property);
            return "/listings/create";
        }else{
            return "redirect:/login";
        }
    }


    @PostMapping("/listings/{id}/edit")
    public String editListing(@PathVariable long id, @ModelAttribute Property property ){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property propertyFromDB = propertyDao.findById(id);
        if(user.getId() == propertyFromDB.getUser().getId()) {
            property.setUser(user);
            propertyDao.save(property);
        }
        return "redirect:/profile/";

    }

    @PostMapping("/listings/{id}/delete")
    public String deleteProperty(@PathVariable long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Property post = propertyDao.getById(id);
        if (currentUser.getId() == post.getUser().getId()) {
            propertyDao.delete(post);
        }
        return "redirect:/profile/" + currentUser.getId();
    }





}
