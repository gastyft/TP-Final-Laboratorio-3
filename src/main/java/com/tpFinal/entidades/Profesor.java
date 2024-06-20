package com.tpFinal.entidades;

import com.tpFinal.excepciones.ExceptionPersonalizada;

import java.io.Serializable;
import java.util.TreeSet;

public class Profesor extends Persona implements Serializable {
  private final TreeSet<Curso> cursos = new TreeSet<>();


  public Profesor(){
    super("PP");
  }

  public Profesor(String nombre, String apellido, String email ) {
    super(nombre, apellido, email ,"PP");
  }
  public boolean addCurso(Curso cursonuevo) throws ExceptionPersonalizada {


    boolean agregarcurso = cursos.stream().anyMatch(curso -> curso.equals(cursonuevo));
    if (!agregarcurso) {
      cursos.add(cursonuevo);
      return true;
    } else {
      throw new ExceptionPersonalizada("Ya estas en el curso");
    }
  }

  public TreeSet<Curso> getCursos() {
    return cursos;
  }

  public void agregarCurso(Curso curso ){
    cursos.add(curso);
  }


}
