package com.example.demo.contoller;


import com.example.demo.model.Competicion;
import com.example.demo.service.CompeticionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competiciones")
@CrossOrigin(origins = "*")
public class CompeticionRestController {

    @Autowired
    private CompeticionService competicionService;

    @GetMapping
    public ResponseEntity<List<Competicion>> getAllCompeticiones() {
        List<Competicion> competiciones = competicionService.findAll();
        return new ResponseEntity<>(competiciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competicion> getCompeticionById(@PathVariable String id) {
        Competicion competicion = competicionService.findById(id);
        return new ResponseEntity<>(competicion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Competicion> createCompeticion(@RequestBody Competicion competicion) {
        Competicion nuevaCompeticion = competicionService.save(competicion);
        return new ResponseEntity<>(nuevaCompeticion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Competicion> updateCompeticion(@PathVariable String id, @RequestBody Competicion competicion) {
        Competicion updatedCompeticion = competicionService.update(id, competicion);
        return new ResponseEntity<>(updatedCompeticion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompeticion(@PathVariable String id) {
        competicionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


