package com.tpFinal.entidades;

import java.util.TreeSet;

public class Profesor extends Persona {
  private final TreeSet<Curso> cursos = new TreeSet<>();

  public Profesor(){
    super("PP");
  }

  public Profesor(String nombre, String apellido, String email, String contrasena) {
    super(nombre, apellido, email, contrasena,"PP");
  }

  public TreeSet<Curso> getCursos() {
    return cursos;
  }

}
