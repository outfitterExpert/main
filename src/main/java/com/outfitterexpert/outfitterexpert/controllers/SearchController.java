package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private PropertyRepository propertyDao;

    public SearchController(PropertyRepository propertyDao) {
        this.propertyDao = propertyDao;
    }

    @PostMapping("/search")
    public String search(@RequestParam String property, @RequestParam String animals, Model model) {
        if (property == null && animals == null) {
            model.addAttribute("listings", propertyDao.findAll());
        } else if (property != null || animals != null) {
            if(property != null){

            }else if(animals != null) {

            }
            model.addAttribute("listings", propertyDao);
            return "listings/index";
        }
        return "listings/index";
    }
}

