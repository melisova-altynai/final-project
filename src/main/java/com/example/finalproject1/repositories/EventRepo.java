package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EventRepo extends CrudRepository<Event, Integer> {
    @Override
    List<Event> findAll();

}

