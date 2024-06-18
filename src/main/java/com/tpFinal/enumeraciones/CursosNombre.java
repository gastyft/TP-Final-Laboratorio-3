package com.tpFinal.enumeraciones;

public enum CursosNombre {
    PROGRAMACION_JAVA(500.0),
    DESARROLLO_WEB(400.0),
    BASES_DE_DATOS(300.0),
    ALGORITMOS(450.0),
    ESTRUCTURAS_DE_DATOS(350.0);

    private final double precio;

    CursosNombre(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}
