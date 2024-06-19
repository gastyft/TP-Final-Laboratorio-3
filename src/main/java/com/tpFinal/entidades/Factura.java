package com.tpFinal.entidades;

import com.tpFinal.enumeraciones.CursosNombre;

import java.util.Objects;

public class Factura {

    private static int nextId = 0;
    private int idFactura;
    private Alumno alumno;
    private CursosNombre curso;
    private double valor;

    public Factura( Alumno alumno, CursosNombre curso) {
        this.idFactura = nextId++;
        this.alumno = alumno;
        this.curso = curso;
        this.valor = curso.getPrecio();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return idFactura == factura.idFactura;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idFactura);
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
