package com.tpFinal.swing;

import com.tpFinal.entidades.Curso;
import com.tpFinal.entidades.Profesor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuProfesor extends JDialog {

    private Profesor profesor;
    private List<Curso> cursosDisponibles;
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton logOutButton;
    private JLabel nombre;
    private JLabel apellido;
    private JLabel mail;
    private JLabel legajo;

    public MenuProfesor(Profesor profesor, List<Curso> cursosDisponibles) {

        this.profesor = profesor;
        this.cursosDisponibles = cursosDisponibles;
        setContentPane(panel1);
        setLocation(600,200);
        setSize(500, 500);
        setModal(true);
        nombre.setText(profesor.getNombre());
        apellido.setText(profesor.getApellido());
        mail.setText(profesor.getEmail());
        legajo.setText(profesor.getLegajo());


        logOutButton.addActionListener(e ->{
            {
                setVisible(false);
            }
        });
    }
}
