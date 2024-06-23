package com.tpFinal.entidades;

import com.tpFinal.enumeraciones.CursosNombre;
import com.tpFinal.repository.Repository;

import java.io.Serializable;
import java.util.Objects;

public class Factura implements Serializable {

    private static int nextId;
    private final int idFactura;
    private static Repository<Alumno> r;

    static { //TODO AUTOINCREMENTAL CON MANEJO DE ARCHIVOS PARA AGREGAR NUEVOS ELEMENTOS
        try {
            r = new Repository<>(Alumno.class); //Se usa el alumno para los id de factura autoincremental
            //y los id de alumno, ya que el alumno es el que persiste su factura

           nextId = r.lastId()+1;
        } catch (Exception e) {
            e.printStackTrace(); // Manejar la excepción según tus necesidades
        }
    }
    private CursosNombre curso;
    private double valor;

    public Factura(){
        this.idFactura=nextId++;
    }
    public Factura( CursosNombre curso) {
        this.idFactura = nextId++;
        this.curso = curso;
        this.valor = curso.getPrecio();
    }

    public int getIdFactura() {
        return idFactura;
    }



    public CursosNombre getCurso() {
        return curso;
    }

    public double getValor() {
        return valor;
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
                
                """,idFactura,curso,valor);
    }
}
