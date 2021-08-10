package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Review;
import com.outfitterexpert.outfitterexpert.repositories.ReviewRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    private final ReviewRepository reviewDao;
    private final UserRepository userDao;

    public ReviewController(ReviewRepository reviewDao, UserRepository userDao ) {
        this.reviewDao = reviewDao;
        this.userDao = userDao;
    }

    @GetMapping("/reviews")
    public String viewPosts(Model model) {
        model.addAttribute("posts", reviewDao.findAll());
        return "postReview/review";
    }

    @GetMapping("/reviews/create")
    public String showCreateForm(Model model) {
        model.addAttribute("review", new Review());
        return "postReview/create";
    }

}
