package com.example.demo.repository;


import com.example.demo.model.Club;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends MongoRepository<Club, String> {
    // Métodos personalizados si son necesarios
}


