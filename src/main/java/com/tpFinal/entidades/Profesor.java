package com.tpFinal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpFinal.excepciones.ExceptionPersonalizada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util. List;

public class Profesor extends Persona implements Serializable {

  private final  List<Curso> cursos = new ArrayList<>();


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

  public  List<Curso> getCursos() {
    return cursos;
  }

  public void agregarCurso(Curso curso ){
    cursos.add(curso);
  }


}
