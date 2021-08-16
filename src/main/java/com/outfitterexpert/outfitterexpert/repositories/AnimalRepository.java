package com.outfitterexpert.outfitterexpert.repositories;

import com.outfitterexpert.outfitterexpert.models.Animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal findById(long id);

//    @Query("SELECT a FROM Animal WHERE name = ?1")
    Animal findByName(String name);

}
