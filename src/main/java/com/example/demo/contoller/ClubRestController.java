package com.example.demo.contoller;


import com.example.demo.model.Club;
import com.example.demo.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubes")
@CrossOrigin(origins = "*")
public class ClubRestController {

    @Autowired
    private ClubService clubService;

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubes() {
        List<Club> clubes = clubService.findAll();
        return new ResponseEntity<>(clubes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable String id) {
        Club club = clubService.findById(id);
        return new ResponseEntity<>(club, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club nuevoClub = clubService.save(club);
        return new ResponseEntity<>(nuevoClub, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable String id, @RequestBody Club club) {
        Club updatedClub = clubService.update(id, club);
        return new ResponseEntity<>(updatedClub, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable String id) {
        clubService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

