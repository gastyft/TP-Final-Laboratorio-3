package com.tpFinal.entidades;

import java.util.TreeSet;

public class Curso {
    private String nombre;
    private Profesor profesor;
    private Fecha fecha;
    private TreeSet<Alumno> alumnosInscritos;

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public TreeSet<Alumno> getAlumnosInscritos() {
        return alumnosInscritos;
    }
}
