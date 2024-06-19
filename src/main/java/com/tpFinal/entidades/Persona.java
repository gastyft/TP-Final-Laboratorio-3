package com.tpFinal.entidades;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Objects;

public abstract class Persona implements Comparable<Persona>,Serializable {
    private static int idPersona = 0;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String legajo;

    public Persona() {
        // Constructor sin argumentos
    }
    public Persona(String legajo) {
        this.legajo = legajo + idPersona++;
    }

    public Persona(String nombre, String apellido, String email, String contrasena,String legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.legajo = legajo + idPersona++;

    }

    public static int getIdPersona() {
        return idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(legajo, persona.legajo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(legajo);
    }
    public int compareTo(Persona persona) {
        return nombre.compareTo(persona.nombre);
    }

    @Override
    public String toString() {
        return String.format("""
                Nombre: %s
                Apellido: %s
                Email: %s
                Contrasena: %s
                Legajo: %s
                """,nombre,apellido,email,contrasena,legajo);
    }
}
