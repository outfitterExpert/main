package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.models.Review;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.PropertyRepository;
import com.outfitterexpert.outfitterexpert.repositories.ReviewRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {
    private final ReviewRepository reviewDao;
    private final UserRepository userDao;
    private final PropertyRepository propertyDao;

    public ReviewController(ReviewRepository reviewDao, UserRepository userDao, PropertyRepository propertyDao ) {
        this.reviewDao = reviewDao;
        this.userDao = userDao;
        this.propertyDao = propertyDao;
    }

    @GetMapping("/reviews")
    public String viewPosts(Model model) {
        model.addAttribute("reviews", reviewDao.findAll());
        return "/postReview/index";
    }


    @GetMapping("/review/{id}")
    public String singleReview(@PathVariable long id, Model model) {
        Review review = reviewDao.getById(id);
        boolean isReviewOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isReviewOwner = currentUser.getId() == review.getUser().getId();
        }
        model.addAttribute("review", review);
        model.addAttribute("isReviewOwner", isReviewOwner);
        return "/postReview/review";
    }

    @GetMapping("/reviews/{id}/create")
    public String showCreateForm(@PathVariable long id, Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("id", id);
        return "/postReview/create";
    }

    @PostMapping("/reviews/{id}/create")
    public String createReview(@PathVariable long id, @ModelAttribute Review review){
        System.out.println(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Property property = propertyDao.findById(id);
//        System.out.println(property.getTitle());
//        System.out.println(property.getId());

        review.setUser(user);
        review.setProperty(propertyDao.findById(id));
        reviewDao.save(review);
        return "redirect:/listings";
    }

    @GetMapping("/reviews/{id}/edit")
    public String editReviewForm(@PathVariable long id, Model model ){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = reviewDao.getById(id);
        if(currentUser.getId() == review.getUser().getId()) {
            model.addAttribute("review", review);
            return "review/edit";
        } else {
            return "redirect:/profile/";
        }
    }


    @PostMapping("/reviews/{id}/edit")
    public String editReview(@PathVariable long id, @ModelAttribute Review review ){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review reviewFromDB = reviewDao.getById(id);
        if(user.getId() == reviewFromDB.getUser().getId()) {
            review.setUser(user);
            reviewDao.save(review);
        }
        return "redirect:/profile/";

    }





}

//For Wed 8-10-21, we will finish review, and then work on edit and delete GET and POST mapping.