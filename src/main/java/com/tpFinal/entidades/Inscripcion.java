package com.tpFinal.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonMerge;

import java.io.Serializable;
import java.util.Objects;

public class Inscripcion implements Comparable<Inscripcion> {

    private Curso curso;
    private Alumno alumno;
    private Factura factura;

    public Inscripcion(Curso curso, Alumno alumno, Factura factura) {
        this.curso = curso;
        this.alumno = alumno;
        this.factura = factura;
    }

    public Curso getCurso() {
        return curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }


    public Factura getFactura() {
        return factura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscripcion that = (Inscripcion) o;
        return Objects.equals(curso, that.curso) && Objects.equals(alumno, that.alumno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curso, alumno);
    }

    @Override
    public int compareTo(Inscripcion o) {
        return this.alumno.compareTo(o.alumno);
    }
}
