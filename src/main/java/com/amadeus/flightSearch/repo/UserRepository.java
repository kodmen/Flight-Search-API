package com.amadeus.flightSearch.repo;

import com.amadeus.flightSearch.entity.meta.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}