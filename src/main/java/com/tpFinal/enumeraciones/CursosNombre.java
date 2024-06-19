package com.tpFinal.enumeraciones;

public enum CursosNombre {
    PROGRAMACION_JAVA(5000.0),
    DESARROLLO_WEB(4000.0),
    BASES_DE_DATOS(3000.0),
    ALGORITMOS(4500.0),
    ESTRUCTURAS_DE_DATOS(3500.0);

    private final double precio;

    CursosNombre(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}
