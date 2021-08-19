package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.ReviewRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final PropertyRepository propertyDao;
    private final ReviewRepository reviewDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, PropertyRepository propertyDao, ReviewRepository reviewDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.propertyDao = propertyDao;
        this.reviewDao = reviewDao;

    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String viewYourProfile(Model model){

        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser"){
            return "redirect:/login";
        }

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", currentUser);
        model.addAttribute("listings", propertyDao.findByUserId(currentUser.getId()));
        model.addAttribute("reviews", reviewDao.findByUserId(currentUser.getId()));

        return "users/profile";
    }

    @GetMapping("/profile/{id}")
    public String viewProfile(@PathVariable long id, Model model){
        User user = userDao.findById(id);

        model.addAttribute("user", user);
        model.addAttribute("listings", propertyDao.findByUserId(id));
        model.addAttribute("reviews", reviewDao.findByUserId(id));


        //check to see if the current user has the same id as the account
        boolean isUserAccount = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isUserAccount = currentUser.getId() == user.getId();
        }

        model.addAttribute("isUserAccount", isUserAccount);

        return "users/profile";
    }
    //take the user to their profile when they click on the nav "profile button"


}
