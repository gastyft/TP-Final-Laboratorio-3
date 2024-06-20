package com.tpFinal.seguridad.entity;


import com.tpFinal.enumeraciones.RolNombre;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;

import java.io.Serializable;

  public class Rol implements Serializable {
   // private static final long serialVersionUID = 1L;


    private int id;


    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}