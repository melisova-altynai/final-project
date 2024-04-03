package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EventRepo extends CrudRepository<Event, Integer> ,
        JpaRepository<Event, Integer> {

    List <Event> findAllByOrganizerId(Integer id);

}

