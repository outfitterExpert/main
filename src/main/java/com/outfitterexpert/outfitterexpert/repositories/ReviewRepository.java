package com.outfitterexpert.outfitterexpert.repositories;

import com.outfitterexpert.outfitterexpert.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findById(long id);
    Review findByTitle(String title);


}
