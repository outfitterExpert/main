package com.outfitterexpert.outfitterexpert.repositories;

import com.outfitterexpert.outfitterexpert.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findById(long id);
    Review findByTitle(String title);

    @Query("FROM Review r where r.property.id = ?1")
    List<Review> findByPropertyId(long id);

    Review save(Review r);


}
