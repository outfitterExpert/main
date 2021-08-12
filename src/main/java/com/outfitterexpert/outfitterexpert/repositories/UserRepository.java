package com.outfitterexpert.outfitterexpert.repositories;

import com.outfitterexpert.outfitterexpert.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByUsername(String username);
    User save(User u);

}
