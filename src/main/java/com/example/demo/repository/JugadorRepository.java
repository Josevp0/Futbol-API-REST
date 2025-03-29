package com.example.demo.repository;


import com.example.demo.model.Jugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends MongoRepository<Jugador, String> {
    // Métodos personalizados si son necesarios
}


