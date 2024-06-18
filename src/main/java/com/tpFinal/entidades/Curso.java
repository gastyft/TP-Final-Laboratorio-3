package com.tpFinal.entidades;

import com.tpFinal.enumeraciones.CursosNombre;

import java.util.TreeSet;

public class Curso implements Comparable<Curso> {
    private CursosNombre cursosNombre;
    private Profesor profesor;
    private Fecha fecha;
    private final TreeSet<Alumno> alumnosInscritos = new TreeSet<>();

    public Curso(CursosNombre nombre) {
        this.cursosNombre = nombre;
    }
    public Curso(CursosNombre cursosNombre, Profesor profesor, Fecha fecha) {
        this.cursosNombre = cursosNombre;
        this.profesor = profesor;
        this.fecha = fecha;
    }

    public CursosNombre getCursosNombre() {
        return cursosNombre;
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
    public void agregarAlumnos(Alumno alumno){
        alumnosInscritos.add(alumno);
    }

    public void setCursosNombre(CursosNombre cursosNombre) {
        this.cursosNombre = cursosNombre;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Curso o) {
        return this.profesor.getLegajo().compareTo(o.getProfesor().getLegajo());
    }
    public boolean compararCurso(Curso o){
     return this.cursosNombre.equals(o.getCursosNombre()) && this.profesor.equals(o.getProfesor()) && this.fecha.equals(o.getFecha());
    }
}
