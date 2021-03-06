package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${MAPBOX_ACCESS_TOKEN}")
    private String MAPBOX_ACCESS_TOKEN;

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
            model.addAttribute("MAPBOX_ACCESS_TOKEN", MAPBOX_ACCESS_TOKEN);
        } else {
            List<Property> locationMatches = propertyDao.findPropertyByLocation(location);
            List<Property> animalMatches = propertyDao.findAllLikeAnimalName(animals);
            if(location.equals("")) {
                model.addAttribute("listings", propertyDao.findAllLikeAnimalName(animals));
                model.addAttribute("MAPBOX_ACCESS_TOKEN", MAPBOX_ACCESS_TOKEN);
                return "listings/index";
            } else if(animals.equals("")) {
                model.addAttribute("listings", propertyDao.findPropertyByLocation(location));
                model.addAttribute("MAPBOX_ACCESS_TOKEN", MAPBOX_ACCESS_TOKEN);
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
                model.addAttribute("MAPBOX_ACCESS_TOKEN", MAPBOX_ACCESS_TOKEN);
                return "listings/index";
            }
        }
        return "listings/index";
    }
}

