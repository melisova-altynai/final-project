package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<Category, Integer> {
    @Override
    List<Category> findAll();
}
