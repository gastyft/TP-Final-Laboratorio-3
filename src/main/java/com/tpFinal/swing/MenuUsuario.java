package com.tpFinal.swing;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Persona;
import com.tpFinal.entidades.Profesor;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.seguridad.SistemaLogin;

import javax.swing.*;

public class MenuUsuario extends JDialog {
    private Persona persona;
    private JPanel panelUsuario;
    private JTextField nombreField1;
    private JTextField apellidoField1;
    private JTextField mailField2;
    private JPasswordField passwordField1;
    private JRadioButton profesorRadioButton;
    private JRadioButton alumnoRadioButton;
    private JButton crearButton;

    private SistemaLogin sistemaLogin = new SistemaLogin();

    public MenuUsuario() {
        setContentPane(panelUsuario);
        setLocation(600, 200);
        setSize(500, 500);
        setModal(true);


        crearButton.addActionListener(e -> {
            {
                if (profesorRadioButton.isSelected()) {

                    persona = new Profesor();
                    validar();
                    try {
                        sistemaLogin.crearUsuario(persona.getNombre(), persona.getNombre(), persona.getEmail(), persona.getContrasena(), (Profesor) persona);
                        JOptionPane.showMessageDialog(MenuUsuario.this, "Profesor creado", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                    } catch (ExceptionPersonalizada ex) {
                        JOptionPane.showMessageDialog(null, "No se pudo crear el Usuario " + ex, "APP", JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();

                } else if (alumnoRadioButton.isSelected()) {

                    persona = new Alumno();
                    validar();
                    try {
                        sistemaLogin.crearUsuario(persona.getNombre(), persona.getNombre(), persona.getEmail(), persona.getContrasena(), (Alumno) persona);
                        JOptionPane.showMessageDialog(MenuUsuario.this, "Alumno creado", "Confirmación", JOptionPane.INFORMATION_MESSAGE);

                    } catch (ExceptionPersonalizada ex) {
                        JOptionPane.showMessageDialog(null, "No se pudo crear el Usuario " + ex, "APP", JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Elija un radio button", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    public void validar() {
        this.persona.setNombre(nombreField1.getText());
        this.persona.setApellido(apellidoField1.getText());
        this.persona.setEmail(mailField2.getText());
        this.persona.setContrasena(passwordField1.getText());
    }

    public Persona getPersona() {
        return persona;
    }
}
