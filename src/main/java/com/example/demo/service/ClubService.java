package com.example.demo.service;

import com.example.demo.model.Club;
import com.example.demo.repository.ClubRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    public Club findById(String id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club no encontrado con id: " + id));
    }

    public Club save(Club club) {
        return clubRepository.save(club);
    }

    public Club update(String id, Club clubDetails) {
        Club club = findById(id);
        
        club.setNombre(clubDetails.getNombre());
        club.setAsociacion(clubDetails.getAsociacion());
        club.setEntrenador(clubDetails.getEntrenador());
        club.setCompeticiones(clubDetails.getCompeticiones());
        
        return clubRepository.save(club);
    }

    public void deleteById(String id) {
        Club club = findById(id);
        clubRepository.delete(club);
    }
}

