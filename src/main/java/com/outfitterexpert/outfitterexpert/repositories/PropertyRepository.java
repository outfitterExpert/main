package com.outfitterexpert.outfitterexpert.repositories;

import com.outfitterexpert.outfitterexpert.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Property findById(long id);

    @Query("FROM Property p where p.user.id = ?1")
    List<Property> findByUserId(long id);

    @Query("FROM Property p where p.location LIKE %?1%")
    List<Property> findPropertyByLocation(String location);

//
    @Query("FROM Property p inner join p.animals a WHERE a.name LIKE %:animals%")
    List<Property> findAllLikeAnimalName(String animals);
//    @Query("FROM Property a where a.animals IN (?1)")
//    List<Property> findPropertyByAnimal(String animal);





    Property findTopByOrderByIdDesc();

}
