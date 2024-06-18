package com.tpFinal.entidades;

import java.util.TreeSet;

public class Curso implements Comparable<Curso> {
    private String nombre;
    private Profesor profesor;
    private Fecha fecha;
    private final TreeSet<Alumno> alumnosInscritos = new TreeSet<>();

    public Curso(String nombre) {
        this.nombre = nombre;
    }
    public Curso(String nombre, Profesor profesor, Fecha fecha) {
        this.nombre = nombre;
        this.profesor = profesor;
        this.fecha = fecha;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Curso o) {
        return this.nombre.compareTo(o.getNombre());
    }
}
