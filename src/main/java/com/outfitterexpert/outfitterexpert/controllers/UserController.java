package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.ReviewRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final PropertyRepository propertyDao;
    private final ReviewRepository reviewDao;

    @Value("${MAPBOX_ACCESS_TOKEN}")
    private String MAPBOX_ACCESS_TOKEN;

    @Value("${FILE_STACK_ACCESS_TOKEN}")
    private String FILE_STACK_ACCESS_TOKEN;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, PropertyRepository propertyDao, ReviewRepository reviewDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.propertyDao = propertyDao;
        this.reviewDao = reviewDao;

    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("FILE_STACK_ACCESS_TOKEN", FILE_STACK_ACCESS_TOKEN);
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);

        if(user.getImg_user().equals("")){
            user.setImg_user("https://cdn.filestackcontent.com/X37zSRiv3Us9kRNMZALR");
        }

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
        model.addAttribute("MAPBOX_ACCESS_TOKEN", MAPBOX_ACCESS_TOKEN);

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


    @GetMapping("/profile/{id}/edit")
    public String editProfileForm(@PathVariable long id, Model model ){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getById(id);
        if(currentUser.getId() == user.getId()) {
            model.addAttribute("user", user);
            model.addAttribute("FILE_STACK_ACCESS_TOKEN", FILE_STACK_ACCESS_TOKEN);
            return "users/sign-up";

        }else{
            return "redirect:/login";
        }
    }



    @PostMapping("/profile/{id}/edit")
    public String editProfile(@PathVariable long id, @ModelAttribute User user ){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User profileFromDB = userDao.findById(id);
        if(user.getId() == profileFromDB.getId()) {
            userDao.save(user);
        }
        return "redirect:/profile/";

    }

    @PostMapping("/profile/{id}/delete")
    public String deleteProfile(@PathVariable long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getById(id);
        if (currentUser.getId() == user.getId()) {
            userDao.delete(user);
        }

        return "redirect:/login";
    }





}
