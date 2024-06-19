package com.tpFinal.seguridad;

import com.tpFinal.entidades.Profesor;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.seguridad.entity.Rol;
import com.tpFinal.seguridad.entity.Usuario;
import com.tpFinal.seguridad.jwt.JwtProvider;

import javax.swing.*;

public class SistemaLogin {
    //TODO crear nuevo usuario y guardar en archivo
    //TODO loguearse
    //TODO ver modificarlo
    JwtProvider jwtProvider = new JwtProvider();


    public void crearUsuario(String nombre, String nombreUsuario, String email, String password, Rol rol, Profesor per) throws ExceptionPersonalizada {

        try {
            Usuario usuario = new Usuario(nombre, nombreUsuario, email, password, rol,per);
            if( jwtProvider.registrarUsuario(usuario)) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente", "Error", JOptionPane.OK_OPTION);
                });
            }
        } catch (Exception e) {
            throw new ExceptionPersonalizada("Error al crear nuevo usuario");
        }
    }

}
