package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private PropertyRepository propertyDao;

    public SearchController(PropertyRepository propertyDao) {
        this.propertyDao = propertyDao;
    }
    @GetMapping("/search")
    public String getSearchForm() {
        return "listings/index";
    }
    @PostMapping("/search")
    public String search(@RequestParam String location, @RequestParam String animals, Model model) {
        System.out.println(animals);
        if (location.equals("") && animals.equals("")) {
            model.addAttribute("listings", propertyDao.findAll());
        } else {
            List<Property> locationMatches = propertyDao.findPropertyByLocation(location);
            List<Property> animalMatches = propertyDao.findAllLikeAnimalName(animals);
            if(locationMatches.isEmpty()) {
                model.addAttribute("listings", propertyDao.findAllLikeAnimalName(animals));
                return "listings/index";
            } else if(animalMatches.isEmpty()) {
                model.addAttribute("listings", propertyDao.findPropertyByLocation(location));
            return "listings/index";
            } else {
                List<Property> listings = new ArrayList<>();
                for(Property aMatch : animalMatches){
                    for(Property lMatch :  locationMatches){
                        if(lMatch.getId() == aMatch.getId()) {
                            listings.add(aMatch);
                            break;
                        }
                    }
                }
                model.addAttribute("listings", listings);
                return "listings/index";
            }
        }
        return "listings/index";
    }
}

