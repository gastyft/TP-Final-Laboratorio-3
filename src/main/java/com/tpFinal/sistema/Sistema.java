package com.tpFinal.sistema;

import com.tpFinal.entidades.*;
import com.tpFinal.enumeraciones.CursosNombre;
import com.tpFinal.enumeraciones.DiaSemana;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.repository.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Sistema {
    private final  TreeSet<Alumno> alumnos = new TreeSet<>();
    private final TreeSet<Profesor> profesores = new TreeSet<>();
    private final TreeSet<Curso> cursos = new TreeSet<>();


    public Sistema() {
           Repository<Alumno> alumnosRepository = new Repository<>(Alumno.class);

        Alumno alumno = new Alumno("Jose", "Lodeiro", "lodes@gmail.com");
        Profesor profesor = new Profesor("Pedro", "Lopez", "lopes@gmail.com");
        LocalDateTime localDateTime = LocalDateTime.now();
        List<DiaSemana> diasemana = Arrays.asList(DiaSemana.LUNES, DiaSemana.MIERCOLES, DiaSemana.VIERNES);
        Fecha fecha = new Fecha(localDateTime, localDateTime.plusHours(4), diasemana);
        Fecha fecha2 = new Fecha(localDateTime, localDateTime.plusDays(2), diasemana);
        Curso curso = new Curso(CursosNombre.ALGORITMOS, profesor, fecha);
        Factura factura = new Factura(alumno, CursosNombre.ALGORITMOS);
        Inscripcion inscripcion = new Inscripcion(curso, alumno, factura);
        Curso curso1 = new Curso(CursosNombre.BASES_DE_DATOS, profesor, fecha2);
        try {
            alumno.addInscripcion(inscripcion);
            alumno.agregarCurso(curso);
        } catch (ExceptionPersonalizada e) {

        }

        alumnos.add(alumno);
        cursos.add(curso);
        cursos.add(curso1);


    }

    public void agregarPersona(Alumno alumno) {
        alumnos.add(alumno);

    }
    public void mostrar(){
        for (Alumno alumno : alumnos){
            System.out.println(alumno);
        }
    }
    public void agregarProfesor(Profesor profesor) {

        profesores.add(profesor);
    }

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    public List<Alumno> devolverAlumnoslist() {
        return alumnos.stream().toList();
    }

    public List<Profesor> devolverProfesoreslist() {
        return profesores.stream().toList();
    }

    public List<Curso> devolverCursoslist() {
        return cursos.stream().toList();
    }

    public Profesor buscarPorLegajoProfesor(String legajoABuscar) {
        Profesor profesor = null;
        for (Profesor profe : profesores) {
            if (profe.getLegajo().equals(legajoABuscar)) {
                profesor = profe;
            }
        }
        return profesor;
    }

    public Alumno buscarPorLegajoAlumno(String legajoABuscar) {
        Alumno alumno = null;
        System.out.println(legajoABuscar);
        for (Alumno alum : alumnos) {
            System.out.println(alum);
            if (alum.getLegajo().equals(legajoABuscar)) {
                alumno = alum;
            }
        }
        return alumno;
    }

}
