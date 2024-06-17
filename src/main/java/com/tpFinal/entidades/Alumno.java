package com.tpFinal.entidades;

import java.util.ArrayList;
import java.util.List;

public class Alumno extends Persona {

    List<Inscripcion> inscripciones;
    List<Curso> cursosPagos = new ArrayList<>();

    public Alumno(String nombre, String apellido, String email, String contraseña) {
        super(nombre, apellido, email, contraseña);
    }

    public Alumno(String nombre, String apellido, String email, String contraseña, List<Inscripcion> inscripciones, List<Curso> cursosPagos) {
        super(nombre, apellido, email, contraseña);
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
