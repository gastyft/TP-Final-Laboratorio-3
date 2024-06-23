package com.tpFinal.excepciones;

import javax.swing.*;

public class ExceptionPersonalizada extends Exception{
    public ExceptionPersonalizada(String message) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
            });
        }

    }


