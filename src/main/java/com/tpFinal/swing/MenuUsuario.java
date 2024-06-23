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

        // Añadir DocumentListener a todos los campos relevantes
        nombreField1.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validateInputs();
            }
            public void removeUpdate(DocumentEvent e) {
                validateInputs();
            }
            public void changedUpdate(DocumentEvent e) {
                validateInputs();
            }
        });
        profesorRadioButton.addActionListener(e -> validateInputs());
        alumnoRadioButton.addActionListener(e -> validateInputs());

        apellidoField1.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validateInputs();
            }
            public void removeUpdate(DocumentEvent e) {
                validateInputs();
            }
            public void changedUpdate(DocumentEvent e) {
                validateInputs();
            }
        });

        mailField2.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validateInputs();
            }
            public void removeUpdate(DocumentEvent e) {
                validateInputs();
            }
            public void changedUpdate(DocumentEvent e) {
                validateInputs();
            }
        });

     /*   passwordField1.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                validateInputs();
            }
            public void removeUpdate(DocumentEvent e) {
                validateInputs();
            }
            public void changedUpdate(DocumentEvent e) {
                validateInputs();
            }
        }); */

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

    private void validateInputs() {
        boolean isNameValid = isValidName(nombreField1.getText());
        boolean isApellidoValid = isValidName(apellidoField1.getText());
        boolean isEmailValid = isValidEmail(mailField2.getText());
      //  boolean isPasswordValid = isValidPassword(new String(passwordField1.getPassword()));
        boolean isRadioButtonSelected = profesorRadioButton.isSelected() || alumnoRadioButton.isSelected();

        crearButton.setEnabled(isRadioButtonSelected && isNameValid && isApellidoValid && isEmailValid); //&& isPasswordValid

         // Cambiar color de fondo según la validez
        nombreField1.setBackground(isNameValid ? Color.GREEN : Color.PINK);
        apellidoField1.setBackground(isApellidoValid ? Color.GREEN : Color.PINK);
        mailField2.setBackground(isEmailValid ? Color.GREEN : Color.PINK);
       // passwordField1.setBackground(isPasswordValid ? Color.WHITE : Color.PINK);
    }

    public boolean isValidName(String name) {
        String nameRegex = "^[a-zA-Z]{4,}$";
        Pattern pattern = Pattern.compile(nameRegex);
        return name != null && pattern.matcher(name).matches();
    }
    public boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
/*
    public boolean isValidPassword(String password) {
        // Debe contener al menos una letra mayúscula, un carácter especial y no tener espacios
        String passwordRegex = "^(?=.*[A-Z])(?=.*[^\\w\\d])[^\\s]+$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }
*/
    public void validar() {
        this.persona.setNombre(nombreField1.getText());
        this.persona.setApellido(apellidoField1.getText());
        this.persona.setEmail(mailField2.getText());
    }

    public Persona getPersona() {
        return persona;
    }
}
