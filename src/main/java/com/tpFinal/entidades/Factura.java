package com.tpFinal.entidades;

import com.tpFinal.enumeraciones.CursosNombre;

public class Factura {

    private static int nextId = 0;
    private int idFactura;
    private Alumno alumno;
    private CursosNombre curso;
    private double valor;

    public Factura(int idFactura, Alumno alumno, CursosNombre curso, double valor) {
        this.idFactura = idFactura;
        this.alumno = alumno;
        this.curso = curso;
        this.valor = valor;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public CursosNombre getCurso() {
        return curso;
    }

    public double getValor() {
        return valor;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public void setCurso(CursosNombre curso) {
        this.curso = curso;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("""
                 idFactura: %d
                 alumno: %s
                  curso: %s
                  valor: %.2f
                
                """,idFactura,alumno,curso,valor);
    }
}
