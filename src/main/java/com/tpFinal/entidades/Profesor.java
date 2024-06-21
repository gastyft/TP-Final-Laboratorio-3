package com.tpFinal.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpFinal.excepciones.ExceptionPersonalizada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util. List;

public class Profesor extends Persona implements Serializable {

   List<Curso> cursos = new ArrayList<>();


  public Profesor(){
    super("PP");
  }

  public Profesor(String nombre, String apellido, String email )
  {
    super(nombre, apellido, email ,"PP");
  }

  public boolean addCurso(Curso cursonuevo) throws ExceptionPersonalizada {


    boolean agregarcurso = this.cursos.stream().anyMatch(curso1 -> curso1.equals(cursonuevo));

    if (!agregarcurso) {
      cursos.add(cursonuevo);
      System.out.println("DALDLASDLSADASDSADSA");
      return true;
    } else {
      throw new ExceptionPersonalizada("Ya estas en el curso");
    }

  }

  public  List<Curso> getCursos() {
    return cursos;
  }



}
