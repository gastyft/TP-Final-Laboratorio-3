package com.tpFinal.swing;

import com.tpFinal.entidades.*;
import com.tpFinal.enumeraciones.RolNombre;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.seguridad.SistemaLogin;
import com.tpFinal.seguridad.entity.Usuario;
import com.tpFinal.sistema.Sistema;
import com.tpFinal.swing.MenuAdmin.MenuAdmin;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MenuPrincipal {
    private JPanel panelprincipal;
    private JTextField UsuarioField1;
    private JLabel usuarioLabel;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton newUserButton;
    private JPanel imagenField;
    private Sistema sistema = new Sistema();
    private SistemaLogin sistemaLogin = new SistemaLogin();



    public MenuPrincipal() {
     // En caso de que haya surgido algún inconveniente con los archivos, podes borrarlos a todos
     // y quieras crear un nuevo admin descomentar este comentario ejecutarlo una vez y luego volver a comentar.
     /*
        try {
            sistemaLogin.crearUsuario("admin","admin","admin@admin.ar","admin","admin",RolNombre.ROL_ADMIN);
        } catch (ExceptionPersonalizada e) {
            throw new RuntimeException(e);
        }
      */
setupImagenField();
        logInButton.addActionListener(e -> {

            String usuario = UsuarioField1.getText();
            //Capturo el Array de char del password Field
            String contraseniaStr = new String(passwordField1.getPassword());
            Usuario usuario1 = sistemaLogin.login(usuario, contraseniaStr); //Convierto el array de
            // caracteres passwordField  a String para trabajarlo dentro del metodo como
            // String(Por plantearlo inicialmente asi)
            // Limpiar el STRING de caracteres después de usarlo para que no quede en memoria
            contraseniaStr ="";
            passwordField1.setText("");
            if (usuario1 != null) {
                JOptionPane.showMessageDialog(null, "BIENVENIDO");
                //TODO BUSCARR POR LEGAJO Y DEVOLVER ALUMNO O PROFESOR

                if (usuario1.getRol().equals(RolNombre.ROL_ALUMNO)) {

                    /// aca asigna si es un alumno y lo manda al menu alumno

                   Alumno alumno = sistema.buscarPorLegajoAlumno(usuario1.getLegajo());


                   // List<Alumno> alumnos = sistema.devolverAlumnoslist();
                    List<Curso> cursos = sistema.devolverCursoslist();




                    menuAlumno menualumno = new menuAlumno(alumno, cursos, sistema);
                    menualumno.setVisible(true);
                    // Esperar hasta que la ventana menualumno se cierre



                    Alumno alumno1 = menualumno.getAlumno();


                    try {

                        sistema.modificar(alumno1); // Actualizar el alumno en el sistema
                         sistema.actualizarCurso(alumno1.getCursosPagos());

                    } catch (ExceptionPersonalizada | IOException ex) {
                        // Manejar excepción si ocurre alguna
                        JOptionPane.showMessageDialog(null, "Error en parte menu Principal parte rol alumno", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else if (usuario1.getRol().equals(RolNombre.ROL_PROFESOR)) {

                    List<Curso> cursos = sistema.devolverCursoslist();
                    Profesor profesor1 = sistema.buscarPorLegajoProfesor(usuario1.getLegajo());
                    MenuProfesor menuProfesor = new MenuProfesor(profesor1, cursos,sistema);
                    menuProfesor.setVisible(true);
                    Profesor profesor2 = menuProfesor.getProfesor();
                    try {
                        sistema.modificarProfesor(profesor2);
                        sistema.agregarCursoProfesor(profesor2.getCursos());

                    }catch (ExceptionPersonalizada ex){
                        JOptionPane.showMessageDialog(null, "Error en menu Principal parte Rol Profesor", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    }
                else if(usuario1.getRol().equals(RolNombre.ROL_ADMIN))
                {
                    MenuAdmin menuAdmin = new MenuAdmin(sistema,sistemaLogin);
                    menuAdmin.setVisible(true);

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
    private void setupImagenField() {
        // Crear un JLabel para la imagen
        JLabel imageLabel = new JLabel();

        // Cargar la imagen desde un archivo y redimensionarla
        try {
            // Cambia la ruta de acuerdo a donde tengas almacenada tu imagen
            File imgFile = new File("src/assets/UTN.jpeg");
            ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath()); // Cargar la imagen como ImageIcon

            // Redimensionar la imagen al tamaño deseado
            Image image = icon.getImage().getScaledInstance(300, 98,Image.SCALE_SMOOTH); // Ancho x Alto.
            // SMOOTH aumenta la calidad de imagen
            icon.setImage(image); // Establecer la imagen redimensionada en el ImageIcon

            imageLabel.setIcon(icon); // Establecer el ImageIcon en el JLabel
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurar el layout del JPanel imagenField
       // imagenField.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10));
        imagenField.setLayout(new FlowLayout());

        // Configurar el layout del JPanel imagenField
        imagenField.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
     //   gbc.gridx = 0;
      //  gbc.gridy = 0;
      //  gbc.gridwidth = 1; // Asegura que ocupa una sola columna
      //  gbc.gridheight = 1; // Asegura que ocupa una sola fila
     //   gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, -80, 0, -70); // Espacios alrededor de la imagen

        // Agregar el JLabel de la imagen al JPanel imagenField
        imagenField.add(imageLabel, gbc);

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuPrincipal");
        frame.setContentPane(new MenuPrincipal().panelprincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // La consola no queda en el borde
        frame.setSize(700, 200);
        frame.setVisible(true);

    }
}
