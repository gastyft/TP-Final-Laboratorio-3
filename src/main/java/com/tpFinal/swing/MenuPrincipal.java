package com.tpFinal.swing;

import com.tpFinal.entidades.*;
import com.tpFinal.enumeraciones.DiaSemana;
import com.tpFinal.enumeraciones.RolNombre;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.seguridad.SistemaLogin;
import com.tpFinal.seguridad.entity.Usuario;
import com.tpFinal.sistema.Sistema;

import javax.swing.*;

import java.util.List;

public class MenuPrincipal {
    private JPanel panelprincipal;
    private JTextField UsuarioField1;
    private JLabel usuarioLabel;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton newUserButton;
    private  Sistema sistema = new Sistema();
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

                   Alumno alumno = sistema.buscarPorLegajoAlumno(usuario1.getLegajo());


                   // List<Alumno> alumnos = sistema.devolverAlumnoslist();
                    List<Curso> cursos = sistema.devolverCursoslist();
                    for(Curso curso : cursos){
                        System.out.println(curso);
                    }

                 // Profesor profesorr = sistema.buscarPorLegajoProfesor("PP1");
                  // MenuProfesor menuProfesor = new MenuProfesor(profesorr,cursos);
                   // menuProfesor.setVisible(true);


                     menuAlumno menualumno = new menuAlumno(alumno , cursos,sistema);
                      menualumno.setVisible(true);

                  /*  if(menualumno.isActive()) {
                        try {
                            sistema.modificar(menualumno.getAlumno());
                            for (Curso curso : menualumno.getCursoInscripcion()) {
                                sistema.modificarCurso(curso);
                            }
                        } catch (ExceptionPersonalizada ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                  */


                 //
              //       try {
                //         Alumno alumno1 = menualumno.getAlumno();
                 //        sistema.modificar(alumno1);

                  //   }catch (ExceptionPersonalizada ex){

                  //   }
                     //  sistema.modificarCurso(menualumno.getCursonuevo());





                //Modificar alumno, curso e incripcion si es que se modifican
                } else if (usuario1.getRol().equals(RolNombre.ROL_PROFESOR)) {
                    Profesor profesor = sistema.buscarPorLegajoProfesor(usuario1.getLegajo());

                    List<Profesor> profesores = sistema.devolverProfesoreslist();

                    // MenuProfesor menuProfesor = new MenuProfesor(usuario1.getProf(),profesores);
                    //   menuProfesor.setVisible(true);
                }
            }


        });
        newUserButton.addActionListener(e -> {

            MenuUsuario menuUsuario = new MenuUsuario();
            menuUsuario.setVisible(true);
            Persona persona = menuUsuario.getPersona();
            if(persona instanceof Alumno alumno)
            sistema.agregarPersona(alumno);
            else if(persona instanceof Profesor profesor)
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
