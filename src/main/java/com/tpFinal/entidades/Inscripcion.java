package com.tpFinal.entidades;

import java.io.Serializable;
import java.util.Objects;

public class Inscripcion implements Comparable<Inscripcion>{
  private Curso curso;
  private Alumno alumno;
  private double valorCurso;
  private Factura factura;

    public Inscripcion(Curso curso, Alumno alumno, double valorCurso, Factura factura) {
        this.curso = curso;
        this.alumno = alumno;
        this.valorCurso = valorCurso;
        this.factura = factura;
    }

    public Curso getCurso() {
        return curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public double getValorCurso() {
        return valorCurso;
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
