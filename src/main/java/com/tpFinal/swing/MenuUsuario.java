package com.tpFinal.swing;

import com.tpFinal.entidades.Persona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

public class MenuUsuario extends JDialog {


    private JPanel panelUsuario;
    private JTextField nombreField1;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JRadioButton profesorRadioButton;
    private JRadioButton alumnoRadioButton;
    private JButton crearButton;

    public  MenuUsuario()
    {   setContentPane(panelUsuario);
        setLocation(600,200);
        setSize(500, 500);
        setModal(true);


        crearButton.addActionListener(e->{
            {
                if(profesorRadioButton.isSelected()){

                } else if (alumnoRadioButton.isSelected()) {

                }

            }
        });
    }
}
