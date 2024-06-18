package com.tpFinal.sistema;

import com.tpFinal.entidades.*;
import com.tpFinal.enumeraciones.DiaSemana;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Sistema {

    private final TreeSet<Persona> personas = new TreeSet<Persona>();
    private final TreeSet<Curso> cursos = new TreeSet<Curso>();
    private final TreeSet<Inscripcion> inscripciones = new TreeSet<Inscripcion>();

   public Sistema(){
       Alumno alumno = new Alumno("Jose","Lodeiro","lodes@gmail.com","123");
       Profesor profesor = new Profesor("Pedro","Lopez","lopes@gmail.com","234");
       LocalDateTime localDateTime = LocalDateTime.now();
       List<DiaSemana> diasemana = Arrays.asList(DiaSemana.LUNES, DiaSemana.MIERCOLES, DiaSemana.VIERNES);
       Fecha fecha = new Fecha(localDateTime,localDateTime.plusHours(4),diasemana);
       Curso curso = new Curso("matematica",profesor,fecha);
       alumno.agregarCurso(curso);
       personas.add(alumno);



   }
   public void agregarPersona(Persona persona){
       personas.add(persona);
   }
   public List<Persona> devolverPersonaslist(){

       return personas.stream().toList();
    }

}
