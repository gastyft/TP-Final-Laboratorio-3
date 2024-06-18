package com.tpFinal.swing;

import com.tpFinal.entidades.*;
import com.tpFinal.enumeraciones.DiaSemana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MenuPrincipal {
    private JPanel panelprincipal;
    private JTextField UsuarioField1;
    private JLabel usuarioLabel;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton newUserButton;


    public MenuPrincipal() {

        logInButton.addActionListener(e -> {


                 /// aca asigna si es un alumno y lo manda al menu alumno
                Alumno alumno = new Alumno("Jose","Lodeiro","lodes@gmail.com","123");
            Profesor profesor = new Profesor("Pedro","Lopez","lopes@gmail.com","234");
                LocalDateTime localDateTime = LocalDateTime.now();
            List<DiaSemana> diasemana = Arrays.asList(DiaSemana.LUNES, DiaSemana.MIERCOLES, DiaSemana.VIERNES);
                 Fecha fecha = new Fecha(localDateTime,localDateTime.plusHours(4),diasemana);
            Curso curso = new Curso("matematica",profesor,fecha);
                 alumno.agregarCurso(curso);
                 ///
                 menuAlumno menualumno = new menuAlumno(alumno);
                 menualumno.setVisible(true);




        });
        newUserButton.addActionListener(e -> {

                MenuUsuario menuUsuario = new MenuUsuario();
                menuUsuario.setVisible(true);
                Persona persona = menuUsuario.getPersona();
                System.out.println(persona);


        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuPrincipal");
        frame.setContentPane(new MenuPrincipal().panelprincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // La consola no queda en el borde
        frame.setSize(500,200);
        frame.setVisible(true);

    }
}
