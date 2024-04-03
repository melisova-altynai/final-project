package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> ,
        JpaRepository<Category, Integer> {
}
