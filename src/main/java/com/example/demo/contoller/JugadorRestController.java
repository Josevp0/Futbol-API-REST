package com.example.demo.contoller;


import com.example.demo.model.Jugador;
import com.example.demo.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@CrossOrigin(origins = "*")
public class JugadorRestController {

    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    public ResponseEntity<List<Jugador>> getAllJugadores() {
        List<Jugador> jugadores = jugadorService.findAll();
        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> getJugadorById(@PathVariable String id) {
        Jugador jugador = jugadorService.findById(id);
        return new ResponseEntity<>(jugador, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Jugador> createJugador(@RequestBody Jugador jugador) {
        Jugador nuevoJugador = jugadorService.save(jugador);
        return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> updateJugador(@PathVariable String id, @RequestBody Jugador jugador) {
        Jugador updatedJugador = jugadorService.update(id, jugador);
        return new ResponseEntity<>(updatedJugador, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJugador(@PathVariable String id) {
        jugadorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

