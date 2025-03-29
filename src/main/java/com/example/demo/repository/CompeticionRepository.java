package com.example.demo.repository;

import com.example.demo.model.Competicion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompeticionRepository extends MongoRepository<Competicion, String> {
    // Métodos personalizados si son necesarios
}

