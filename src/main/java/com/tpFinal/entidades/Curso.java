package com.tpFinal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpFinal.enumeraciones.CursosNombre;
import com.tpFinal.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class Curso implements Comparable<Curso> {
    private static int idCursoAuto;
    private final int idCurso;
    private static Repository<Curso> r;


    static { //TODO AUTOINCREMENTAL CON MANEJO DE ARCHIVOS PARA AGREGAR NUEVOS ELEMENTOS
        try {
            r = new Repository<>(Curso.class);
            idCursoAuto = r.lastId()+1;
        } catch (Exception e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
        }
    }
    private CursosNombre cursosNombre;

    private String nombreProfesor;
    private Fecha fecha;
    private final List<String> alumnosInscriptos = new ArrayList<>();//TODO ALMACENA NOMBRE CONCATENADO CON APELLIDO

    public Curso(CursosNombre nombre) {
        this.idCurso=idCursoAuto++;
        this.cursosNombre = nombre;
    }
    public Curso(CursosNombre cursosNombre, String profesor, Fecha fecha) {
        this.idCurso=idCursoAuto++;
        this.cursosNombre = cursosNombre;
        this.nombreProfesor = profesor;
        this.fecha = fecha;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public CursosNombre getCursosNombre() {
        return cursosNombre;
    }

    public String getProfesor() {
        return nombreProfesor;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public List<String> getAlumnosInscriptos() {
        return alumnosInscriptos;
    }
    public void agregarAlumnos(Alumno alumno){
        alumnosInscriptos.add(alumno.getNombre()+ " " + alumno.getApellido());
    }

    public void setCursosNombre(CursosNombre cursosNombre) {
        this.cursosNombre = cursosNombre;
    }

    public void setProfesor(String profesor) {
        this.nombreProfesor = profesor;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return cursosNombre == curso.cursosNombre && Objects.equals(nombreProfesor, curso.nombreProfesor) && Objects.equals(fecha, curso.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursosNombre, nombreProfesor, fecha);
    }

    @Override
    public int compareTo(Curso o) {
        return this.cursosNombre.compareTo(o.getCursosNombre());
    }
    public boolean compararCurso(Curso o){
     return this.cursosNombre.equals(o.getCursosNombre()) && this.nombreProfesor.equals(o.getProfesor()) && this.fecha.equals(o.getFecha());
    }
}
