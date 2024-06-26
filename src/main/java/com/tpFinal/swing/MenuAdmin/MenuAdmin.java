package com.tpFinal.swing.MenuAdmin;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Curso;
import com.tpFinal.entidades.Profesor;
import com.tpFinal.enumeraciones.CursosNombre;
import com.tpFinal.enumeraciones.RolNombre;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.seguridad.SistemaLogin;
import com.tpFinal.sistema.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuAdmin  extends JDialog {

    private List<Alumno> alumnos;
    private List<Profesor> profesores;
    private List<Curso> cursos;
    Sistema sistemaPrincipal;
    SistemaLogin sistemaLogin;
    private JButton crearUsuarioButton;
    private JPanel panelPrincipal;
    private JButton accederAlMenuButton;
    private JButton logOutButton;
    JFrame eliminarFrame = new JFrame("Eliminar Alumno de Curso");
    JFrame crearUsuarioFrame = new JFrame("Crear ADMIN");
    JFrame frame = new JFrame("Cambiar Profesor de Curso");
    JFrame frame2 = new JFrame("Menú Principal");
    //   private Map<String, String> usuarios; // Mapa de usuarios y contraseñas
    //  private Admin admin;

    public MenuAdmin(Sistema sistemaPrincipal, SistemaLogin sistemaLogin) {


        this.alumnos = sistemaPrincipal.devolverAlumnoslist();
        this.profesores = sistemaPrincipal.devolverProfesoreslist();
        this.cursos = sistemaPrincipal.devolverCursoslist();
        this.sistemaPrincipal = sistemaPrincipal;
        this.sistemaLogin = sistemaLogin;

        setContentPane(panelPrincipal);
        setLocation(600, 200);
        setSize(500, 500);
        setModal(true);


        accederAlMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mostrarMenu();
            }
        });
        crearUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuarioAdmin(); //crea usuario Admin
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }



    public void agregarUsuario(String usuario, String contrasenia) {
        // Verificar si el usuario ya existe antes de agregarlo
        try {
            RolNombre rol = RolNombre.ROL_ADMIN;
            sistemaLogin.crearUsuario("admin", usuario, "admin@admin.com", contrasenia, "ADMIN", rol);
              JOptionPane.showMessageDialog(crearUsuarioFrame, "Usuario admin creado exitosamente.");
        } catch (ExceptionPersonalizada ex) {
            JOptionPane.showMessageDialog(null, "Error al crear el usuario Admin", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    public void crearUsuarioAdmin() {
        MenuAdmin.this.setVisible(false);

        crearUsuarioFrame.setSize(300, 200);
        JPanel crearUsuarioPanel = new JPanel(new GridLayout(4, 2));

        JTextField nuevoUsuarioField = new JTextField();
        JPasswordField nuevaContrasenaField = new JPasswordField();
        JButton agregarUsuarioButton = new JButton("Agregar ADMIN");

        crearUsuarioPanel.add(new JLabel("Nuevo Admin:"));
        crearUsuarioPanel.add(nuevoUsuarioField);
        crearUsuarioPanel.add(new JLabel("Nueva Contraseña Admin:"));
        crearUsuarioPanel.add(nuevaContrasenaField);
        crearUsuarioPanel.add(agregarUsuarioButton);

        crearUsuarioFrame.add(crearUsuarioPanel);
        crearUsuarioFrame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        crearUsuarioFrame.setVisible(true);

        agregarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoUsuario = nuevoUsuarioField.getText();
                String nuevaContrasena = new String(nuevaContrasenaField.getPassword());

                agregarUsuario(nuevoUsuario, nuevaContrasena);

                nuevoUsuarioField.setText("");
                nuevaContrasenaField.setText("");
                crearUsuarioFrame.dispose();

                MenuAdmin.this.setVisible(true);
            }
        });
    }


    private void mostrarMenu() {
        MenuAdmin.this.setVisible(false);

        frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame2.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(5, 1));
        //Si queda tiempo agregamos modificar
        JButton eliminarAlumnoDeCursoButton = new JButton("Eliminar Alumno de Curso");
        JButton cambiarProfesorDeCursoButton = new JButton("Cambiar Profesor de Curso");
        JButton salirButton = new JButton("Volver atrás");

        panel.add(eliminarAlumnoDeCursoButton);
        panel.add(cambiarProfesorDeCursoButton);
        panel.add(salirButton);

        frame2.add(panel);
        frame2.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame2.setVisible(true);


        eliminarAlumnoDeCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                eliminarAlumnoDeCurso();


            }
        });

        cambiarProfesorDeCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cambiarProfesorDeCurso();


            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.dispose();
                MenuAdmin.this.setVisible(true);
            }
        });
    }

    private void eliminarAlumnoDeCurso() {
        this.frame2.setVisible(false);

        eliminarFrame.setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JTextField legajoAlumnoField = new JTextField();
        JComboBox<CursosNombre> nombreCursoComboBox = new JComboBox<>(CursosNombre.values());
        JButton eliminarButton = new JButton("Eliminar");

        panel.add(new JLabel("Legajo del Alumno:"));
        panel.add(legajoAlumnoField);
        panel.add(new JLabel("Nombre del Curso:"));
        panel.add(nombreCursoComboBox);
        panel.add(eliminarButton);

        eliminarFrame.add(panel);
        eliminarFrame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        eliminarFrame.setVisible(true);

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String legajoAlumno = legajoAlumnoField.getText().toUpperCase();
                CursosNombre cursoNombreEnum = (CursosNombre) nombreCursoComboBox.getSelectedItem();
                legajoAlumnoField.setText("");
                Alumno alumno = sistemaPrincipal.buscarPorLegajoAlumno(legajoAlumno);
                if (alumno == null) {
                    JOptionPane.showMessageDialog(eliminarFrame, "No se encontró ningún alumno con ese legajo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Curso curso = sistemaPrincipal.buscarCursoPorNombre(cursoNombreEnum);
                if (curso == null) {
                    JOptionPane.showMessageDialog(eliminarFrame, "No se encontró ningún curso con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    try {


                       if( alumno.getCursosPagos().remove(curso) &&  curso.eliminarAlumno(alumno)) {
                           List<Curso> curso1 = new ArrayList<>();
                           curso1.add(curso);
                           sistemaPrincipal.modificar(alumno);
                           sistemaPrincipal.actualizarCurso(curso1);

                           JOptionPane.showMessageDialog(eliminarFrame, "Alumno eliminado del curso exitosamente.");
                       }
                       else  JOptionPane.showMessageDialog(eliminarFrame, "Alumno no tenia curso asignado");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(eliminarFrame, "Error al eliminar alumno del curso.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                eliminarFrame.dispose();
                frame2.setVisible(true);

            }

        });
        eliminarFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame2.setVisible(true);
            }
        });

        frame2.setVisible(false);
    }



    private void cambiarProfesorDeCurso() {
        frame2.setVisible(false);
        frame.setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JTextField legajoProfesorField = new JTextField();
        JComboBox<CursosNombre> nombreCursoComboBox = new JComboBox<>(CursosNombre.values());
        JButton cambiarButton = new JButton("Cambiar");

        panel.add(new JLabel("Legajo del Profesor:"));
        panel.add(legajoProfesorField);
        panel.add(new JLabel("Nombre del Curso:"));
        panel.add(nombreCursoComboBox);
        panel.add(cambiarButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);

        cambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String legajoProfesor = legajoProfesorField.getText().toUpperCase();
                CursosNombre nombreCurso = (CursosNombre) nombreCursoComboBox.getSelectedItem();
                legajoProfesorField.setText("");
                Profesor profesor = sistemaPrincipal.buscarPorLegajoProfesor(legajoProfesor);
                if (profesor == null) {
                    JOptionPane.showMessageDialog(frame, "No se encontró ningún profesor con ese legajo.", "Error", JOptionPane.ERROR_MESSAGE);
                  return;
                }

                String profesorViejo;

                Curso curso = sistemaPrincipal.buscarCursoPorNombre(nombreCurso);
                profesorViejo = curso.getProfesor();
                Profesor profeViejo = sistemaPrincipal.buscarPofesorPorNombre(profesorViejo);

                if (curso == null) {
                    JOptionPane.showMessageDialog(frame, "No se encontró ningún curso con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
                } else {
                    curso.setProfesor(profesor.getNombre() + " " + profesor.getApellido());

                    try {
                        profesor.addCurso(curso); //ya verifica el addCurso
                        List<Curso> cursoList = new ArrayList<>();
                        cursoList.add(curso);
                        sistemaPrincipal.actualizarCurso(cursoList);

                        Curso cursoEliminar = profeViejo.getCursos().get(profeViejo.getCursos().indexOf(curso));
                        profeViejo.getCursos().remove(cursoEliminar);
                        sistemaPrincipal.modificarProfesor(profeViejo);

                        JOptionPane.showMessageDialog(frame, "Profesor cambiado en el curso exitosamente.");

                    } catch (ExceptionPersonalizada | IOException ex) {
                        JOptionPane.showMessageDialog(frame, "No se pudo cambiar", "Sistema", JOptionPane.ERROR_MESSAGE);
                    }
                }
                frame.dispose();
                frame2.setVisible(true);
            }
        });

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame2.setVisible(true);
            }
        });

        frame2.setVisible(false);
    }

}