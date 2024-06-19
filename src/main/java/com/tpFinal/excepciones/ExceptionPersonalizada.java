package com.tpFinal.excepciones;

import javax.swing.*;

public class ExceptionPersonalizada extends Exception{
    public ExceptionPersonalizada(String message) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
            });
        }

    }

    //TODO VER SI FUNCIONA LLAMANDO A JOPTIONMESSAGE DE SWING. Deberia funcionar porque es independiente
    //TODO a lo que ocurra. ENtonces si lanza excepcion lanzaria es cartelito de Swing con el mensaje
    //TODO deseado personalizado

