package com.tpFinal.entidades;

import com.tpFinal.enumeraciones.DiaSemana;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Fecha {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    public List<DiaSemana> diasemana;
    public Fecha() {
    }
    public Fecha(LocalDateTime fechaInicio, LocalDateTime fechaFin, List<DiaSemana> diasemana) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasemana = diasemana;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public List<DiaSemana> getDiasemana() {
        return diasemana;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDiasemana(List<DiaSemana> diasemana) {
        this.diasemana = diasemana;
    }
}
