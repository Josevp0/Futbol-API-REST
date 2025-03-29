package com.example.demo.repository;


import com.example.demo.model.Asociacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsociacionRepository extends MongoRepository<Asociacion, String> {
    // Métodos personalizados si son necesarios
}

