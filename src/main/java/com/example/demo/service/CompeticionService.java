package com.example.demo.service;


import com.example.demo.model.Competicion;
import com.example.demo.repository.CompeticionRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompeticionService {

    @Autowired
    private CompeticionRepository competicionRepository;

    public List<Competicion> findAll() {
        return competicionRepository.findAll();
    }

    public Competicion findById(String id) {
        return competicionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Competición no encontrada con id: " + id));
    }

    public Competicion save(Competicion competicion) {
        return competicionRepository.save(competicion);
    }

    public Competicion update(String id, Competicion competicionDetails) {
        Competicion competicion = findById(id);
        
        competicion.setNombre(competicionDetails.getNombre());
        competicion.setMontoPremio(competicionDetails.getMontoPremio());
        competicion.setFechaInicio(competicionDetails.getFechaInicio());
        competicion.setFechaFin(competicionDetails.getFechaFin());
        
        return competicionRepository.save(competicion);
    }

    public void deleteById(String id) {
        Competicion competicion = findById(id);
        competicionRepository.delete(competicion);
    }
}

