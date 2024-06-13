package com.tpFinal.excepciones;

public class ExceptionPersonalizada extends Exception{
    public ExceptionPersonalizada(String message) {
        super(message);
    }

    //TODO VER SI FUNCIONA LLAMANDO A JOPTIONMESSAGE DE SWING. Deberia funcionar porque es independiente
    //TODO a lo que ocurra. ENtonces si lanza excepcion lanzaria es cartelito de Swing con el mensaje
    //TODO deseado personalizado
}
