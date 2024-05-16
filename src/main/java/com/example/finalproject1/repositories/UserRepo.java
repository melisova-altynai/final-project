package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>,
        JpaRepository<User, Integer> {

    Optional<User> findById(int id);

    boolean existsByUsername(String username);
    User findByUsername(String username);

    User findByEmail(String email);
}

