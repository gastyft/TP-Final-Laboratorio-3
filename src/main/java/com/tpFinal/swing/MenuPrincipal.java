package com.tpFinal.swing;

import com.tpFinal.entidades.*;
import com.tpFinal.enumeraciones.DiaSemana;
import com.tpFinal.enumeraciones.RolNombre;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.seguridad.SistemaLogin;
import com.tpFinal.seguridad.entity.Usuario;
import com.tpFinal.sistema.Sistema;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MenuPrincipal {
    private JPanel panelprincipal;
    private JTextField UsuarioField1;
    private JLabel usuarioLabel;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton newUserButton;
    private Sistema sistema = new Sistema();
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
                //TODO BUSCARR POR LEGAJO Y DEVOLVER ALUMNO O PROFESOR

                if (usuario1.getRol().equals(RolNombre.ROL_ALUMNO)) {

                    /// aca asigna si es un alumno y lo manda al menu alumno
                    System.out.println(usuario1.getLegajo());
                    Alumno alumno = sistema.buscarPorLegajoAlumno(usuario1.getLegajo());

                    List<Alumno> alumnos = sistema.devolverAlumnoslist();
                    List<Curso> cursos = sistema.devolverCursoslist();


                    menuAlumno menualumno = new menuAlumno(alumno, cursos, sistema);
                    menualumno.setVisible(true);
                    // Esperar hasta que la ventana menualumno se cierre


                    Alumno alumno1 = menualumno.getAlumno();
                    List<Curso> cursosInsc =menualumno.getCursoInscripcion();
                    Curso c =menualumno.getCursonuevo();
                    try {

                        sistema.modificar(alumno1); // Actualizar el alumno en el sistema
                         sistema.actualizarCurso(alumno1.getCursosPagos());
                     //   sistema.modificarCurso(menualumno.getCursonuevo());
                       // sistema.verificarAlumnosEnCurso(alumno1); // Verificar inscripción en cursos
                    } catch (ExceptionPersonalizada ex) {
                        // Manejar excepción si ocurre alguna
                        ex.printStackTrace(); // o imprimir un mensaje de error
                    }
                    //Modificar alumno, curso e incripcion si es que se modifican
                } else if (usuario1.getRol().equals(RolNombre.ROL_PROFESOR)) {
                    Profesor profesor = sistema.buscarPorLegajoProfesor(usuario1.getLegajo());
                    List<Profesor> profesores = sistema.devolverProfesoreslist();
                    List<Curso> cursos = sistema.devolverCursoslist();
                    Profesor profesor1 = sistema.buscarPorLegajoProfesor(usuario1.getLegajo());
                    MenuProfesor menuProfesor = new MenuProfesor(profesor1, cursos);
                    menuProfesor.setVisible(true);
                }
            }


        });

        newUserButton.addActionListener(e -> {

            MenuUsuario menuUsuario = new MenuUsuario();
            menuUsuario.setVisible(true);
            Persona persona = menuUsuario.getPersona();
            if (persona instanceof Alumno alumno)
                sistema.agregarPersona(alumno);
            else if (persona instanceof Profesor profesor)
                sistema.agregarProfesor(profesor);
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
