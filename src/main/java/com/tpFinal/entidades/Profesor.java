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

  public Profesor(String nombre, String apellido, String email )
  {
    super(nombre, apellido, email ,"PP");
  }

  public boolean addCurso(Curso cursonuevo) throws ExceptionPersonalizada
  {

    boolean horario = this.cursos.stream().anyMatch(curso -> curso.compararCurso(cursonuevo) && curso.compararDia(cursonuevo));
    if (!horario) {
      cursos.add(cursonuevo);
      return true;
    } else {
      throw new ExceptionPersonalizada("Ya estas en el curso");
    }
  }
  public boolean eliminarCurso(Curso curso) throws ExceptionPersonalizada {
      if(!curso.tieneAlumnosInscriptos()){
        cursos.remove(curso);
        return true;
      }else{
        throw new ExceptionPersonalizada("el Curso tiene alumnos");
      }

  }

  public  List<Curso> getCursos() {
    return cursos;
  }



}
