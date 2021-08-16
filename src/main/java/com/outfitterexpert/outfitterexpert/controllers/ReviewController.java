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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/reviews/{id}/create")
    public String showCreateForm(@PathVariable long id, Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("id", id);
        return "/postReview/create";
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

    @PostMapping("/reviews/create")
    public String createAd(@ModelAttribute Review review) {
        review.setUser(userDao.getById(1L));
        reviewDao.save(review);
        return "redirect:/review";

    }

    @PostMapping("/reviews/{id}/create")
    public String createReview(@ModelAttribute long landId, @ModelAttribute Review review){
        System.out.println(landId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Property property = propertyDao.findById(id);
//        System.out.println(property.getTitle());
//        System.out.println(property.getId());

        review.setUser(user);
        review.setProperty(propertyDao.findById(landId));
        reviewDao.save(review);
        return "redirect:/listings";
    }



}

//For Wed 8-10-21, we will finish review, and then work on edit and delete GET and POST mapping.