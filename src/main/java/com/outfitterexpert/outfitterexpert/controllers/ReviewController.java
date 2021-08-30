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
        return "postReview/index";
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
        return "postReview/review";
    }

    @GetMapping("/reviews/{listing_id}/create")
    public String showCreateForm(@PathVariable long listing_id, Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser"){
            model.addAttribute("review", new Review());
            model.addAttribute("listing_id", listing_id);
            return "postReview/create";
        }else{
            return "redirect:/login";
        }

    }

    @PostMapping("/reviews/{listing_id}/create")
    public String createReview(@PathVariable long listing_id, @ModelAttribute Review review){
        System.out.println(listing_id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Property property = propertyDao.findById(id);
//        System.out.println(property.getTitle());
//        System.out.println(property.getId());

        review.setUser(user);
        review.setProperty(propertyDao.findById(listing_id));
        reviewDao.save(review);
        return "redirect:/listings";
    }

    @GetMapping("/reviews/{id}/edit")
    public String editReviewForm(@PathVariable long id, Model model ){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = reviewDao.findById(id);
        if(currentUser.getId() == review.getUser().getId()) {
            model.addAttribute("review", review);
            return "postReview/create";
        }else{
            return "redirect:/login";
        }
    }


    @PostMapping("/reviews/{id}/edit")
    public String editReview(@PathVariable long id, @ModelAttribute Review review ){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review reviewFromDB = reviewDao.findById(id);
        if(user.getId() == reviewFromDB.getUser().getId()) {
            review.setUser(user);
            reviewDao.save(review);
        }
        return "redirect:/profile/";

    }

    @PostMapping("/reviews/{id}/delete")
    public String deleteReview(@PathVariable long id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review post = reviewDao.getById(id);
        if (currentUser.getId() == post.getUser().getId()) {
            reviewDao.delete(post);
        }
        return "redirect:/profile/" + currentUser.getId();
    }





}

//For Wed 8-10-21, we will finish review, and then work on edit and delete GET and POST mapping.