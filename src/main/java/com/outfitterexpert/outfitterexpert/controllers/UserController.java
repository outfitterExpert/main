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
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PresentationDirection;
import javax.validation.Valid;
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
    public String saveUser(Model model, @Valid @ModelAttribute User user, Errors result, @RequestParam(defaultValue = "false") boolean outfitter, @RequestParam String signUpPasswordConfirm){
        String hash = passwordEncoder.encode(user.getPassword());
        if(userDao.existsByUsername(user.getUsername())){
            result.rejectValue("username", "user.username", "This username already exists.");
        }
        if(userDao.existsByEmail(user.getEmail())){
            result.rejectValue("email", "user.email", "This email already exists.");
        }
        if(!signUpPasswordConfirm.equals(user.getPassword())){
            result.rejectValue("password", "user.password", "Passwords dont match");
        }
        if(result.hasErrors()){
            model.addAttribute("errors", result);
            model.addAttribute("user", user);
            return "users/sign-up";
        }
        if(outfitter){
            user.setOutfitter(true);
        }

        if(user.getImg_user().equals("")){
            user.setImg_user("https://cdn.filestackcontent.com/X37zSRiv3Us9kRNMZALR");
        }
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
        boolean outfitterStatus = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isUserAccount = currentUser.getId() == user.getId();
            outfitterStatus = currentUser.isOutfitter();
        }

        model.addAttribute("isUserAccount", isUserAccount);
        model.addAttribute("outfitterStatus", outfitterStatus);

        return "users/profile";
    }
    //take the user to their profile when they click on the nav "profile button"


    @GetMapping("/profile/edit")
    public String editProfileForm(Model model ){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.getById(currentUser.getId());
        model.addAttribute("user", user);
        model.addAttribute("FILE_STACK_ACCESS_TOKEN", FILE_STACK_ACCESS_TOKEN);
        return "users/edit";

    }



    @PostMapping("/profile/edit")
    public String editProfile(Model model, @Valid @ModelAttribute("user") User user, Errors result, @RequestParam(defaultValue = "false") boolean outfitter){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User updateAccount = userDao.getById(currentUser.getId());

        if(result.hasErrors()){
            model.addAttribute("errors", result);
            model.addAttribute("user", user);
            return "users/edit";
        }

        if(outfitter){
            user.setOutfitter(true);
        }
        if(user.getImg_user().equals("") || user.getImg_user().equals("https://cdn.filestackcontent.com/X37zSRiv3Us9kRNMZALR")){
            user.setImg_user("https://cdn.filestackcontent.com/X37zSRiv3Us9kRNMZALR");
        }

        updateAccount.setUsername(user.getUsername());
        updateAccount.setEmail(user.getEmail());
        updateAccount.setFirstName(user.getFirstName());
        updateAccount.setLastName(user.getLastName());
        updateAccount.setBio(user.getBio());
        updateAccount.setOutfitter(user.isOutfitter());
        updateAccount.setImg_user(user.getImg_user());
        updateAccount.setUser_location(user.getUser_location());
        updateAccount.setPassword(user.getPassword());

//        model.addAttribute("user", updateAccount);
        userDao.save(updateAccount);

        return "redirect:/profile/" + currentUser.getId();

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
