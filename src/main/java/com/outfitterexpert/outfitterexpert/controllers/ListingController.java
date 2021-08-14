package com.outfitterexpert.outfitterexpert.controllers;


import com.outfitterexpert.outfitterexpert.models.ListingPackage;
import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.PackageRepository;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListingController {
    private final PropertyRepository propertyDao;
    private final UserRepository userDao;
    private final PackageRepository packageDao;


    public ListingController(PropertyRepository propDao, UserRepository userDao, PackageRepository packageDao) {
        this.propertyDao = propDao;
        this.userDao = userDao;
        this.packageDao = packageDao;
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
            model.addAttribute("animals", "");
            return "listings/create";
        }
        return "redirect:/listings";
    }

    @PostMapping("/listings/create")
    public String submitListing(@ModelAttribute Property listing, @ModelAttribute String animals){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User u = userDao.findById(1L);

        listing.setUser(currentUser);
        propertyDao.save(listing);

        System.out.println(animals);
        System.out.println(listing.getTitle());
        System.out.println(listing.getLocation());
        System.out.println(listing.getSlots());
        System.out.println(listing.isLodging());
        System.out.println(listing.getUser().getId());

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
