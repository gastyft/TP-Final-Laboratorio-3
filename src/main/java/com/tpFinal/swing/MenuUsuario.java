package com.tpFinal.swing;

import javax.swing.*;

public class MenuUsuario extends JDialog {


    private JPanel panelUsuario;
    private JTextField nombreField1;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;

    public MenuUsuario()
    {   setContentPane(panelUsuario);
        setLocation(600,200);
        setSize(500, 500);
        setModal(true);


    }
}
