package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    @Override
    List<User> findAll();


}

