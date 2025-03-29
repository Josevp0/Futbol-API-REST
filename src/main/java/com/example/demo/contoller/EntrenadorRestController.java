package com.example.demo.contoller;


import com.example.demo.model.Entrenador;
import com.example.demo.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
public class EntrenadorRestController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    public ResponseEntity<List<Entrenador>> getAllEntrenadores() {
        List<Entrenador> entrenadores = entrenadorService.findAll();
        return new ResponseEntity<>(entrenadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> getEntrenadorById(@PathVariable String id) {
        Entrenador entrenador = entrenadorService.findById(id);
        return new ResponseEntity<>(entrenador, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Entrenador> createEntrenador(@RequestBody Entrenador entrenador) {
        Entrenador nuevoEntrenador = entrenadorService.save(entrenador);
        return new ResponseEntity<>(nuevoEntrenador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> updateEntrenador(@PathVariable String id, @RequestBody Entrenador entrenador) {
        Entrenador updatedEntrenador = entrenadorService.update(id, entrenador);
        return new ResponseEntity<>(updatedEntrenador, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrenador(@PathVariable String id) {
        entrenadorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

