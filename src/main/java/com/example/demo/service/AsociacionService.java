package com.example.demo.service;

import com.example.demo.model.Asociacion;
import com.example.demo.repository.AsociacionRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsociacionService {

    @Autowired
    private AsociacionRepository asociacionRepository;

    public List<Asociacion> findAll() {
        return asociacionRepository.findAll();
    }

    public Asociacion findById(String id) {
        return asociacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asociación no encontrada con id: " + id));
    }

    public Asociacion save(Asociacion asociacion) {
        // Para crear un nuevo registro, el ID debe ser nulo
        return asociacionRepository.save(asociacion);
    }

    public Asociacion update(String id, Asociacion asociacionDetails) {
        // Buscar la asociación existente
        Asociacion asociacion = findById(id);
        
        // Actualizar los campos
        asociacion.setNombre(asociacionDetails.getNombre());
        asociacion.setPais(asociacionDetails.getPais());
        asociacion.setPresidente(asociacionDetails.getPresidente());
        
        // Guardar la asociación actualizada
        return asociacionRepository.save(asociacion);
    }

    public void deleteById(String id) {
        Asociacion asociacion = findById(id);
        asociacionRepository.delete(asociacion);
    }
}

