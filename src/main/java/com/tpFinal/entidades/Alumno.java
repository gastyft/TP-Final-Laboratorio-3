package com.tpFinal.entidades;

import java.util.ArrayList;
import java.util.List;

public class Alumno extends Persona {

    List<Inscripcion> inscripciones;
    List<Curso> cursosPagos = new ArrayList<>();

    public Alumno() {
        super("AA");
    }

    public Alumno(String nombre, String apellido, String email, String contrasena) {
        super(nombre, apellido, email, contrasena,"AA");
    }

    public Alumno(String nombre, String apellido, String email, String contrasena, List<Inscripcion> inscripciones, List<Curso> cursosPagos) {
        super(nombre, apellido, email, contrasena,"AA");
        this.inscripciones = inscripciones;
        this.cursosPagos = cursosPagos;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public List<Curso> getCursosPagos() {
        return cursosPagos;
    }
  public void agregarCurso(Curso curso) {
        cursosPagos.add(curso);
  }

}
