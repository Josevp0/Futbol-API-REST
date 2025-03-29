package com.example.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

@Document(collection = "clubes")
public class Club {
    @Id
    private String id;
    
    private String nombre;
    
    @DBRef
    private Asociacion asociacion;
    
    @DBRef
    private Entrenador entrenador;
    
    @DBRef
    private List<Competicion> competiciones;

    public Club() {
    }

    public Club(String nombre, Asociacion asociacion, Entrenador entrenador, List<Competicion> competiciones) {
        this.nombre = nombre;
        this.asociacion = asociacion;
        this.entrenador = entrenador;
        this.competiciones = competiciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Competicion> getCompeticiones() {
        return competiciones;
    }

    public void setCompeticiones(List<Competicion> competiciones) {
        this.competiciones = competiciones;
    }
}

