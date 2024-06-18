package com.tpFinal.seguridad.entity;

import com.tpFinal.entidades.Persona;
import com.tpFinal.enumeraciones.RolNombre;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String nombreUsuario;
    private String email;
    private String password;
    private RolNombre rol;
    private Persona pers;

    public Usuario(String nombre, String nombreUsuario, String email, String password,Rol rol,Persona pers) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.rol = rol.getRolNombre();
        this.pers = pers;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolNombre getRol() {
        return rol;
    }

    public void setRol(RolNombre rol) {
        this.rol = rol;
    }

    public Persona getPers() {
        return pers;
    }

    public void setPers(Persona pers) {
        this.pers = pers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getNombre(), usuario.getNombre()) && Objects.equals(getNombreUsuario(), usuario.getNombreUsuario()) && Objects.equals(getEmail(), usuario.getEmail()) && Objects.equals(getPassword(), usuario.getPassword()) && rol == usuario.rol && Objects.equals(pers, usuario.pers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getNombreUsuario(), getEmail(), getPassword(), rol, pers);
    }
}