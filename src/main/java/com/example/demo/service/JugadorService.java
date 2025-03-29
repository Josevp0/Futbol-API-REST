package com.example.demo.service;

import com.example.demo.model.Jugador;
import com.example.demo.repository.JugadorRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    public Jugador findById(String id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jugador no encontrado con id: " + id));
    }

    public Jugador save(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public Jugador update(String id, Jugador jugadorDetails) {
        Jugador jugador = findById(id);
        
        jugador.setNombre(jugadorDetails.getNombre());
        jugador.setApellido(jugadorDetails.getApellido());
        jugador.setNumero(jugadorDetails.getNumero());
        jugador.setPosicion(jugadorDetails.getPosicion());
        jugador.setClub(jugadorDetails.getClub());
        
        return jugadorRepository.save(jugador);
    }

    public void deleteById(String id) {
        Jugador jugador = findById(id);
        jugadorRepository.delete(jugador);
    }
}

