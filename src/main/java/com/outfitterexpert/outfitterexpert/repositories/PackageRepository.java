package com.outfitterexpert.outfitterexpert.repositories;
import com.outfitterexpert.outfitterexpert.models.ListingPackage;
import com.outfitterexpert.outfitterexpert.models.Property;
import com.outfitterexpert.outfitterexpert.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PackageRepository extends JpaRepository<ListingPackage, Long> {

    ListingPackage findById(long id);

    ListingPackage save(ListingPackage p);

    @Query("FROM ListingPackage p where p.property.id = ?1")
    List<ListingPackage> findByPropertyId(long id);

}
