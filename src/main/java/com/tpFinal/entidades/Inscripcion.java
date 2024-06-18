package com.tpFinal.entidades;

public class Inscripcion {
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

}
