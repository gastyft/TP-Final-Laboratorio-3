package com.tpFinal.swing;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Curso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal {
    private JPanel panelprincipal;
    private JTextField UsuarioField1;
    private JLabel usuarioLabel;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton newUserButton;


    public MenuPrincipal() {

        logInButton.addActionListener(e -> {

             { /// aca asigna si es un alumno y lo manda al menu alumno
                Alumno alumno = new Alumno("Jose","Lodeiro","lodes@gmail.com","fddsfs");
                 Curso curso = new Curso("matematica");
                 alumno.agregarCurso(curso);
                 menuAlumno menualumno = new menuAlumno(alumno);
                 menualumno.setVisible(true);



            }
        });
        newUserButton.addActionListener(e -> {
            {
                MenuUsuario menuUsuario = new MenuUsuario();
                menuUsuario.setVisible(true);

            }
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
