package com.example.demo.repository;


import com.example.demo.model.Entrenador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {
    // Métodos personalizados si son necesarios
}

