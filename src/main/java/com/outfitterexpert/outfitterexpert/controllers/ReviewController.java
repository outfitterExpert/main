package com.outfitterexpert.outfitterexpert.controllers;

import com.outfitterexpert.outfitterexpert.models.Review;
import com.outfitterexpert.outfitterexpert.models.User;
import com.outfitterexpert.outfitterexpert.repositories.ReviewRepository;
import com.outfitterexpert.outfitterexpert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("reviews", reviewDao.findAll());
        return "/postReview/review";
    }

    @GetMapping("/reviews/create")
    public String showCreateForm(Model model) {
        model.addAttribute("review", new Review());
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

//    @PostMapping("/review")

}
