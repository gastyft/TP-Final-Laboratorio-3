package com.tpFinal.entidades;

public abstract class Persona {
    private static int idPersona = 0;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private String legajo;


    public Persona(String nombre, String apellido, String email, String contraseña) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.legajo = "AA++";
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

    public String getContraseña() {
        return contraseña;
    }

    public String getLegajo() {
        return legajo;
    }

}
