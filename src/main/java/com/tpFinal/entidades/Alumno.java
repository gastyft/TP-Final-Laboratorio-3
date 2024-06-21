package com.tpFinal.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tpFinal.excepciones.ExceptionPersonalizada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Alumno extends Persona {

    List<Curso> cursosPagos = new ArrayList<>();

    private List<Factura> listFacturas = new ArrayList<>();;

    public Alumno() {
        super("AA");
    }

    public Alumno(String nombre, String apellido, String email) {
        super(nombre, apellido, email ,"AA");
    }

    public Alumno(String nombre, String apellido, String email , List<Curso> cursosPagos) {
        super(nombre, apellido, email ,"AA");

        this.cursosPagos = cursosPagos;
    }


    public void setCursosPagos(List<Curso> cursosPagos) {
        this.cursosPagos = cursosPagos;
    }

    public List<Factura> getListFacturas() {
        return listFacturas;
    }

    public void setListFacturas(List<Factura> listFacturas) {
        this.listFacturas = listFacturas;
    }

    public List<Curso> getCursosPagos() {
        return cursosPagos;
    }
    public boolean addInscripcion(Curso inscripcion) throws ExceptionPersonalizada { //TODO

        Curso cursoInscripcion = inscripcion;

        boolean cursoPagado = cursosPagos.stream().anyMatch(curso -> curso.getCursosNombre().equals(cursoInscripcion.getCursosNombre()));
        if (!cursoPagado) {
            // inscripciones.add(inscripcion);
              cursosPagos.add(inscripcion);
            return true;
        } else {
           throw new ExceptionPersonalizada("Ya estas inscripto");
        }
    }


    public void agregarFactura(Factura factura){
        listFacturas.add(factura);

    }
}
