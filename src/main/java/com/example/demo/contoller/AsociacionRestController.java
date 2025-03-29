package com.example.demo.contoller;

import com.example.demo.model.Asociacion;
import com.example.demo.service.AsociacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asociaciones")
@CrossOrigin(origins = "*")
public class AsociacionRestController {

    @Autowired
    private AsociacionService asociacionService;

    @GetMapping
    public ResponseEntity<List<Asociacion>> getAllAsociaciones() {
        List<Asociacion> asociaciones = asociacionService.findAll();
        return new ResponseEntity<>(asociaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asociacion> getAsociacionById(@PathVariable String id) {
        Asociacion asociacion = asociacionService.findById(id);
        return new ResponseEntity<>(asociacion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Asociacion> createAsociacion(@RequestBody Asociacion asociacion) {
        Asociacion nuevaAsociacion = asociacionService.save(asociacion);
        return new ResponseEntity<>(nuevaAsociacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asociacion> updateAsociacion(@PathVariable String id, @RequestBody Asociacion asociacion) {
        Asociacion updatedAsociacion = asociacionService.update(id, asociacion);
        return new ResponseEntity<>(updatedAsociacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsociacion(@PathVariable String id) {
        asociacionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


