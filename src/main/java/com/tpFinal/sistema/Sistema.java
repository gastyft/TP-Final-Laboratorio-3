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
    private final List<Alumno> alumnos;
    private final List<Profesor> profesores;
    private final List<Curso> cursos;
    private final List<Inscripcion> inscripcions;
    private final Repository<Alumno> alumnosRepository = new Repository<>(Alumno.class);
    private final Repository<Profesor> profesorRepository = new Repository<>(Profesor.class);
    private final Repository<Curso> cursoRepository = new Repository<>(Curso.class);
    private final Repository<Inscripcion> inscripcionRepository = new Repository<>(Inscripcion.class);

    public Sistema() {
        alumnos = alumnosRepository.listar();
        profesores = profesorRepository.listar();
        cursos = cursoRepository.listar();
        inscripcions = inscripcionRepository.listar();

        //    Alumno alumno = new Alumno("Jose", "Lodeiro", "lodes@gmail.com");
        Profesor profesor = new Profesor("Pedro", "Lopez", "lopes@gmail.com");
        LocalDateTime localDateTime = LocalDateTime.now();
        List<DiaSemana> diasemana = Arrays.asList(DiaSemana.LUNES, DiaSemana.MIERCOLES, DiaSemana.VIERNES);
        Fecha fecha = new Fecha(localDateTime, localDateTime.plusHours(4), diasemana);
        Fecha fecha2 = new Fecha(localDateTime, localDateTime.plusDays(2), diasemana);
        Curso curso = new Curso(CursosNombre.ALGORITMOS, profesor.getNombre(), fecha);
        //   Factura factura = new Factura( CursosNombre.ALGORITMOS);
        //  Inscripcion inscripcion = new Inscripcion(curso, alumno, factura);
        Curso curso1 = new Curso(CursosNombre.BASES_DE_DATOS, profesor.getNombre(), fecha2);
        //   try {
        //     alumno.addInscripcion(inscripcion);
        //     alumno.agregarCurso(curso);
        // } catch (ExceptionPersonalizada e) {
        //  }
        //   alumnos.add(alumno);

       // agregarCurso(curso);
        //  agregarInscripciones(inscripcion);
        //agregarCurso(curso1);


    }

    public void agregarPersona(Alumno alumno) {

        alumnosRepository.agregar(alumno);
        alumnos.add(alumno);   //TODO ACTUALIZAR LA LISTA ACTUAL PARA QUE FUNCIONE EN SESIONN SINO
        //TODO HAY QUE CERRAR Y VOLVER A ABRIR PARA QUE TRABAJE SOBRE ESE ELEMENTO
    }

    public void agregarProfesor(Profesor profesor) {

        // profesores.add(profesor);
        profesorRepository.agregar(profesor);
        profesores.add(profesor);
    }

    public void agregarCurso(Curso curso3) {
        //   cursos.add(curso3);

        cursoRepository.agregar(curso3);
        cursos.add(curso3);
    }

    public List<Alumno> devolverAlumnoslist() {
        return alumnos;
    }

    public List<Profesor> devolverProfesoreslist() {
        return profesores;
    }

    public List<Curso> devolverCursoslist() {
        return cursos;
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
        for (Alumno alum : alumnosRepository.listar()) {
            if (alum.getLegajo().equals(legajoABuscar)) {
                alumno = alum;
            }
        }
        return alumno;
    }

    public void modificar(Alumno alumno) throws ExceptionPersonalizada {
        // Buscar el alumno en la lista interna del sistema
        Alumno alumnoArchivo = alumnosRepository.listar().stream()
                .filter(a -> a.getLegajo().equals(alumno.getLegajo()))
                .findFirst()
                .orElseThrow(() -> new ExceptionPersonalizada("Alumno no encontrado"));

        int indexAlumno = alumnos.indexOf(alumnoArchivo);
        if (indexAlumno != -1) {
            // Actualizar el alumno en la lista interna
            alumnos.set(indexAlumno, alumno);
            // Actualizar el alumno en el repositorio
            alumnosRepository.modificar(alumno, indexAlumno);
        } else {
            throw new ExceptionPersonalizada("Alumno no encontrado en la lista interna");
        }
    }

    public void modificarCurso(Curso curso) throws ExceptionPersonalizada {
        // Buscar el curso en la lista interna del sistema
        Curso cursoArchivo = cursoRepository.listar().stream()
                .filter(c -> c.getIdCurso() == curso.getIdCurso())
                .findFirst()
                .orElseThrow(() -> new ExceptionPersonalizada("Curso no encontrado"));

        int indexCurso = cursos.indexOf(cursoArchivo);

        if (indexCurso != -1) {
            // Actualizar el curso en la lista interna
            cursos.set(indexCurso, curso);
            // Actualizar el curso en el repositorio
            cursoRepository.modificar(curso, indexCurso);
        } else {
            throw new ExceptionPersonalizada("Curso no encontrado en la lista interna");
        }
    }
    public void modificarProfesor(Profesor profesor) throws ExceptionPersonalizada {
            // Buscar el profesor en la lista interna del sistema
            Profesor profesorArchivo = profesorRepository.listar().stream()
                    .filter(a -> a.getLegajo().equals(profesor.getLegajo()))
                    .findFirst()
                    .orElseThrow(() -> new ExceptionPersonalizada("Alumno no encontrado"));

            int indexProfesor = profesores.indexOf(profesorArchivo);
            if (indexProfesor != -1) {
                // Actualizar el Profesor en la lista interna
                profesores.set(indexProfesor, profesor);
                // Actualizar el Profesor en el repositorio
                profesorRepository.modificar(profesor, indexProfesor);
            } else {
                throw new ExceptionPersonalizada("Profesor no encontrado en la lista interna");
            }
        }



        public void agregarInscripciones(Inscripcion inscripcion) {
        inscripcions.add(inscripcion);
        inscripcionRepository.agregar(inscripcion);
    }

    public void actualizarCursos(List<Curso> nuevosCursosPorAlumno) throws ExceptionPersonalizada {
        // Recorremos todos los cursos existentes en el repositorio
        for (Curso cursoExistente : cursos) {
            // Buscamos si existe un curso nuevo por alumno que coincida con el curso existente
            for (Curso nuevoCurso : nuevosCursosPorAlumno) {
                // Comparamos los cursos por nombre y fecha
                if (cursoExistente.compararCurso(nuevoCurso.getCursosNombre())) {
                    // Iteramos sobre los nombres de los alumnos inscritos en el nuevo curso
                    for (String nombreAlumno : nuevoCurso.getAlumnosInscriptos()) {
                        // Si el nombre del alumno no está en la lista de alumnos inscritos del curso existente, lo agregamos
                        if (!cursoExistente.getAlumnosInscriptos().contains(nombreAlumno)) {
                            cursoExistente.agregarAlumnos(nombreAlumno);
                        }
                    }
                    // Guardamos los cambios en el repositorio después de agregar todos los alumnos necesarios
                    try {
                        modificarCurso(cursoExistente);
                    } catch (ExceptionPersonalizada e) {
                        e.printStackTrace(); // Manejar la excepción apropiadamente
                    }
                }
            }
        }
    }

    public void actualizarCurso(List<Curso> cursosPagos) throws ExceptionPersonalizada {
        List<Curso> cursoss = cursos;
        for (Curso cursoPagado : cursosPagos) {
            for (int i = 0; i < cursoss.size(); i++) {
                if (cursoss.get(i).getIdCurso() == cursoPagado.getIdCurso()) {
                    cursoss.set(i, cursoPagado);
                    modificarCurso(cursoPagado);
                    actualizarAlumnosConCurso(cursoPagado);
                    actualizarProfesoresConCurso(cursoPagado);
                    break;
                }
            }
        }

    }

    private void actualizarAlumnosConCurso(Curso curso) throws ExceptionPersonalizada {
        List<Alumno> alumnosActualizados = new ArrayList<>();

        for (Alumno alumno : alumnosRepository.listar()) {
            boolean cursoActualizado = false;
            for (int i = 0; i < alumno.getCursosPagos().size(); i++) {
                if (alumno.getCursosPagos().get(i).getIdCurso() == curso.getIdCurso()) {
                    alumno.getCursosPagos().set(i, curso);
                    cursoActualizado = true;
                }
            }
            if (cursoActualizado) {
                alumnosActualizados.add(alumno);
            }
        }

        for (Alumno alumnoActualizado : alumnosActualizados) {
            // Actualizar el alumno en la lista interna si ya existe
            int indexAlumno = alumnos.indexOf(alumnoActualizado);
            if (indexAlumno != -1) {
                alumnos.set(indexAlumno, alumnoActualizado);
            } else {
                // Si no existe en la lista interna, agregarlo
                alumnos.add(alumnoActualizado);
            }
            // Actualizar el alumno en el repositorio
            alumnosRepository.modificar(alumnoActualizado, alumnos.indexOf(alumnoActualizado));
        }
    }

    public void agregarCursoProfesor(List<Curso> cursosNuevosProfesor) throws ExceptionPersonalizada {
        // Cargar la lista completa de cursos desde el repositorio
        List<Curso> cursosExistentes = cursoRepository.listar();

        for (Curso nuevoCurso : cursosNuevosProfesor) {
            // Verificar si el curso ya existe en la lista completa
            boolean cursoExiste = cursosExistentes.stream()
                    .anyMatch(curso -> curso.getIdCurso() == nuevoCurso.getIdCurso());
            if (!cursoExiste) {
              agregarCurso(nuevoCurso);
            }
        }
    }

    private void actualizarProfesoresConCurso(Curso curso) throws ExceptionPersonalizada {
        List<Profesor> profesoresActualizados = new ArrayList<>();

        for (Profesor profesor : profesorRepository.listar()) {
            boolean cursoActualizado = false;
            for (int i = 0; i < profesor.getCursos().size(); i++) {
                if (profesor.getCursos().get(i).getIdCurso() == curso.getIdCurso()) {
                    profesor.getCursos().set(i, curso);
                    cursoActualizado = true;
                }
            }
            if (cursoActualizado) {
                profesoresActualizados.add(profesor);
            }
        }

        for (Profesor profesorActualizado : profesoresActualizados) {
            // Actualizar el profesor en la lista interna si ya existe
            int indexProfesor = profesores.indexOf(profesorActualizado);
            if (indexProfesor != -1) {
                profesores.set(indexProfesor, profesorActualizado);
            } else {
                // Si no existe en la lista interna, agregarlo
                profesores.add(profesorActualizado);
            }
            // Actualizar el profesor en el repositorio
            profesorRepository.modificar(profesorActualizado, profesores.indexOf(profesorActualizado));
        }
    }

}



