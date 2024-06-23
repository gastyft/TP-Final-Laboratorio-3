package com.tpFinal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpFinal.enumeraciones.CursosNombre;
import com.tpFinal.repository.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Curso implements Serializable, Comparable<Curso>  {

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
    public Curso() {
       idCurso=idCursoAuto++;
        // Constructor por defecto necesario para la deserialización
    }
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
        String nombreCompleto = alumno.getNombre() + " " + alumno.getApellido();
        if (!alumnosInscriptos.contains(nombreCompleto)) {
            alumnosInscriptos.add(nombreCompleto);
             actualizarAlumnosInscriptosEnRepo(); // Actualizar el curso en el repositorio
        }
    }

     private void actualizarAlumnosInscriptosEnRepo() {
        try {
            r.modificar(this, r.listar().indexOf(this)); // r es tu repositorio de cursos
        } catch (Exception e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
        }
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
        return idCurso == curso.idCurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursosNombre, nombreProfesor, fecha);
    }

     @Override
    public int compareTo(Curso o) {
        return this.cursosNombre.compareTo(o.getCursosNombre());
    }

    public boolean compararCurso(Curso o) {
        if (o == null || o.fecha == null || o.fecha.getFechaInicio() == null) {
            return false;
        }
        if (this.fecha == null || this.fecha.getFechaInicio() == null) {
            return false;
        }
        return this.fecha.getFechaInicio().equals(o.fecha.getFechaInicio());
    }
    public boolean compararDia(Curso o) {
        if (o == null || o.fecha == null || o.fecha.diasemana == null) {
            return false;
        }
        if (this.fecha == null || this.fecha.diasemana == null) {
            return false;
        }
        return this.fecha.diasemana.equals(o.fecha.diasemana);
    }

    public boolean compararCurso(CursosNombre nombre) {
        return this.cursosNombre == nombre;
    }

}
