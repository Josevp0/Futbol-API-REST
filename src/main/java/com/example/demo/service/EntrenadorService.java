package com.example.demo.service;


import com.example.demo.model.Entrenador;
import com.example.demo.repository.EntrenadorRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }

    public Entrenador findById(String id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrenador no encontrado con id: " + id));
    }

    public Entrenador save(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public Entrenador update(String id, Entrenador entrenadorDetails) {
        Entrenador entrenador = findById(id);
        
        entrenador.setNombre(entrenadorDetails.getNombre());
        entrenador.setApellido(entrenadorDetails.getApellido());
        entrenador.setEdad(entrenadorDetails.getEdad());
        entrenador.setNacionalidad(entrenadorDetails.getNacionalidad());
        
        return entrenadorRepository.save(entrenador);
    }

    public void deleteById(String id) {
        Entrenador entrenador = findById(id);
        entrenadorRepository.delete(entrenador);
    }
}

