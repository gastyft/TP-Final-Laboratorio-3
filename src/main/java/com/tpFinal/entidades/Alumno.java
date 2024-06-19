package com.tpFinal.entidades;

import com.tpFinal.excepciones.ExceptionPersonalizada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Alumno extends Persona {

    List<Inscripcion> inscripciones = new ArrayList<>();
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
    public boolean addInscripcion(Inscripcion inscripcion) throws ExceptionPersonalizada {

        Curso cursoInscripcion = inscripcion.getCurso();

        boolean cursoPagado = cursosPagos.stream().anyMatch(curso -> curso.equals(cursoInscripcion));
        if (!cursoPagado) {
            inscripciones.add(inscripcion);
            return true;
        } else {
           throw new ExceptionPersonalizada("Ya estas inscripto");
        }
    }
  public void agregarCurso(Curso curso) {
        cursosPagos.add(curso);

  }





}
