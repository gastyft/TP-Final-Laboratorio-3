package com.tpFinal.swing;

import com.tpFinal.entidades.Curso;
import com.tpFinal.entidades.Profesor;

import javax.swing.*;
import java.util.List;

public class MenuProfesor {

    private Profesor profesor;
    private List<Curso> cursosDisponibles;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton button1;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel mail;
    private JLabel legajo;

    public MenuProfesor(Profesor profesor, List<Curso> cursosDisponibles) {
        
        this.profesor = profesor;
        this.cursosDisponibles = cursosDisponibles;





    }
}
