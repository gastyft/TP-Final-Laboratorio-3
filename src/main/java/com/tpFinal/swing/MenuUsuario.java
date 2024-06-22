package com.tpFinal.swing;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Persona;
import com.tpFinal.entidades.Profesor;
import com.tpFinal.enumeraciones.RolNombre;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.seguridad.SistemaLogin;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Pattern;

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

        crearButton.setEnabled(false); // Deshabilitar botón al inicio

        mailField2.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validateEmail();
            }
            public void removeUpdate(DocumentEvent e) {
                validateEmail();
            }
            public void changedUpdate(DocumentEvent e) {
                validateEmail();
            }
        });

        crearButton.addActionListener(e -> {
            if (profesorRadioButton.isSelected()) {
                persona = new Profesor(); //NO FUNCIONA PARA SISTEMA
                validar();
                try {
                    RolNombre rol = RolNombre.ROL_PROFESOR;
                    if (sistemaLogin.crearUsuario(persona.getNombre(), persona.getNombre(), persona.getEmail(), passwordField1.getText(), persona.getLegajo(), rol)) {
                        JOptionPane.showMessageDialog(MenuUsuario.this, "Profesor creado", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ExceptionPersonalizada ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el Usuario " + ex, "APP", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            } else if (alumnoRadioButton.isSelected()) {
                persona = new Alumno(); //NO FUNCIONA PARA SISTEMA
                validar();
                try {
                    RolNombre rol = RolNombre.ROL_ALUMNO;
                    if (sistemaLogin.crearUsuario(persona.getNombre(), persona.getNombre(), persona.getEmail(), passwordField1.getText(), persona.getLegajo(), rol)) {
                        JOptionPane.showMessageDialog(MenuUsuario.this, "Alumno creado", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ExceptionPersonalizada ex) {
                    JOptionPane.showMessageDialog(null, "No se pudo crear el Usuario " + ex, "APP", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Elija un radio button", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void validateEmail() {
        String email = mailField2.getText();
        boolean isValid = isValidEmail(email);
        crearButton.setEnabled(isValid);
        if (!isValid) {
            mailField2.setBackground(Color.PINK);
        } else {
            mailField2.setBackground(Color.WHITE);
        }
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public void validar() {
        this.persona.setNombre(nombreField1.getText());
        this.persona.setApellido(apellidoField1.getText());
        this.persona.setEmail(mailField2.getText());
    }

    public Persona getPersona() {
        return persona;
    }
}
