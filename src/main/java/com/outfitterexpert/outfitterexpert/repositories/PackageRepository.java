package com.outfitterexpert.outfitterexpert.repositories;
import com.outfitterexpert.outfitterexpert.models.ListingPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface PackageRepository extends JpaRepository<ListingPackage, Long> {
    ListingPackage findById(long id);
    ListingPackage save(ListingPackage p);
}
