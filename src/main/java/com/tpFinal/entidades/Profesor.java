package com.tpFinal.entidades;

import java.util.TreeSet;

public class Profesor extends Persona {
  private final TreeSet<Curso> cursos = new TreeSet<>();

  public Profesor(String nombre, String apellido, String email, String contraseña) {
    super(nombre, apellido, email, contraseña);
  }

  public TreeSet<Curso> getCursos() {
    return cursos;
  }

}
