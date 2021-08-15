package com.outfitterexpert.outfitterexpert.repositories;

import com.outfitterexpert.outfitterexpert.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Property findById(long id);

    @Query("FROM Property p where p.user.id = ?1")
    List<Property> findByUserId(long id);

    Property findTopByOrderByIdDesc();

}
