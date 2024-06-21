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
    private final  List<Alumno> alumnos;
    private final  List<Profesor> profesores;
    private final  List<Curso> cursos;
    private final  List<Inscripcion> inscripcions;
    private Repository<Alumno> alumnosRepository = new Repository<>(Alumno.class);
    private Repository<Profesor> profesorRepository = new Repository<>(Profesor.class);
    private Repository<Curso> cursoRepository = new Repository<>(Curso.class);
    private Repository<Inscripcion> inscripcionRepository = new Repository<>(Inscripcion.class);

    public Sistema() {


        Alumno alumno = new Alumno("Jose", "Lodeiro", "lodes@gmail.com");
        Profesor profesor = new Profesor("Pedro", "Lopez", "lopes@gmail.com");
        LocalDateTime localDateTime = LocalDateTime.now();
        List<DiaSemana> diasemana = Arrays.asList(DiaSemana.LUNES, DiaSemana.MIERCOLES, DiaSemana.VIERNES);
        Fecha fecha = new Fecha(localDateTime, localDateTime.plusHours(4), diasemana);
        Fecha fecha2 = new Fecha(localDateTime, localDateTime.plusDays(2), diasemana);
        Curso curso = new Curso(CursosNombre.ALGORITMOS, profesor.getNombre(), fecha);
        Factura factura = new Factura( CursosNombre.ALGORITMOS);
        alumno.agregarFactura(factura);
        Curso curso1 = new Curso(CursosNombre.BASES_DE_DATOS, profesor.getNombre(), fecha2);
        //   try {
        //     alumno.addInscripcion(inscripcion);
        //     alumno.agregarCurso(curso);
        // } catch (ExceptionPersonalizada e) {
        //  }
        //   alumnos.add(alumno);
        alumnos = new ArrayList<>(alumnosRepository.listar());
        profesores = new ArrayList<>(profesorRepository.listar());
        cursos = new ArrayList<>(cursoRepository.listar());
        inscripcions= new ArrayList<>(inscripcionRepository.listar());

        agregarCurso(curso1);
        agregarCurso(curso);
        agregarPersona(alumno);
        agregarProfesor(profesor);


    }

    public void agregarPersona(Alumno alumno) {
        alumnos.add(alumno);
        alumnosRepository.agregar(alumno);

    }

    public void agregarProfesor(Profesor profesor) {

        profesores.add(profesor);
        profesorRepository.agregar(profesor);
    }

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
        cursoRepository.agregar(curso);
    }

    public List<Alumno> devolverAlumnoslist() {
        return alumnos;
    }

    public List<Profesor> devolverProfesoreslist() {
        return profesores ;
    }

    public List<Curso> devolverCursoslist() {
        return cursos ;
    }

    public List<Inscripcion> devolverInscripcionsList() {
        return inscripcions.stream().toList();
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
        for (Alumno alum : alumnos) {
            if (alum.getLegajo().equals(legajoABuscar)) {
                alumno = alum;
            }
        }
        return alumno;
    }

    public void modificar(Alumno alumno) throws ExceptionPersonalizada {
       /* Alumno alumnoArchivo = alumnosRepository.listar().get(alumnosRepository.listar().indexOf(alumno));
        int indexAlumno = alumnosRepository.listar().indexOf(alumnoArchivo);
        System.out.println(indexAlumno);
        alumnoArchivo.setNombre(alumno.getNombre());
        alumnoArchivo.setApellido(alumno.getApellido());
        alumnoArchivo.setEmail(alumno.getEmail());
        alumnoArchivo.setInscripciones(new ArrayList<>(alumno.getInscripciones()));
        alumnosRepository.modificar(alumnoArchivo, indexAlumno);
        */
        alumnosRepository.modificar(alumno, alumnosRepository.listar().indexOf(alumno));
    }

    public void modificarCurso(Curso curso) {
        cursoRepository.modificar(curso, alumnosRepository.listar().indexOf(curso));
    }
public void agregarInscripciones(Inscripcion inscripcion)
{
    inscripcions.add(inscripcion);
    inscripcionRepository.agregar(inscripcion);
}
}



