package com.tpFinal.swing;

import com.tpFinal.entidades.*;
import com.tpFinal.enumeraciones.DiaSemana;
import com.tpFinal.seguridad.SistemaLogin;
import com.tpFinal.seguridad.entity.Usuario;
import com.tpFinal.sistema.Sistema;

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
    private final Sistema sistema = new Sistema();

    private SistemaLogin sistemaLogin = new SistemaLogin();

    public MenuPrincipal() {

        logInButton.addActionListener(e -> {

            String usuario = UsuarioField1.getText();
              //Capturo el Array de char del password Field
            String contraseniaStr = new String(passwordField1.getPassword());
            Usuario usuario1 = sistemaLogin.login(usuario, contraseniaStr); //Convierto el array a String para
            //trabajarlo dentro del metodo como String(Por plantearlo inicialmente asi)
            // Limpiar el STRING de caracteres después de usarlo para que no quede en memoria
            contraseniaStr = null;

            if (usuario1 != null) {
                JOptionPane.showMessageDialog(null, "BIENVENIDO");
                if(usuario1.getAlum()!= null){
                    /// aca asigna si es un alumno y lo manda al menu alumno
                    List<Alumno> alumnos = sistema.devolverAlumnoslist();
                    List<Curso> cursos = sistema.devolverCursoslist();
                    List<Profesor>profesores = sistema.devolverProfesoreslist();
                    Alumno alumno = alumnos.getFirst();
                    Profesor profesor = profesores.getFirst();
                    sistema.agregarPersona(usuario1.getAlum());

                    menuAlumno menualumno = new menuAlumno(usuario1.getAlum(),cursos);
                    menualumno.setVisible(true);
                }
                else if(usuario1.getProf()!=null){

                    List<Profesor> profesores = sistema.devolverProfesoreslist();
                   Profesor profesor = profesores.getFirst();

                  sistema.agregarProfesor(usuario1.getProf());
                 // MenuProfesor menuProfesor = new MenuProfesor(usuario1.getProf(),profesores);
               //   menuProfesor.setVisible(true);
                }
            }




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
        frame.setSize(500, 200);
        frame.setVisible(true);

    }
}
