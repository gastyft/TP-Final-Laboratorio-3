package com.tpFinal.seguridad;


import com.tpFinal.enumeraciones.RolNombre;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.seguridad.entity.Usuario;
import com.tpFinal.seguridad.jwt.JwtProvider;
import javax.swing.*;

public class SistemaLogin {

    JwtProvider jwtProvider = new JwtProvider();


    public boolean crearUsuario(String nombre, String nombreUsuario, String email, String password, String legajo, RolNombre rol) throws ExceptionPersonalizada {
        try {
            Usuario usuario = new Usuario(nombre, nombreUsuario, email.toLowerCase(), password, rol, legajo);
            if (jwtProvider.registrarUsuario(usuario)) {
            /*    SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente", "APP", JOptionPane.INFORMATION_MESSAGE);
                }); mostraba dos carteles de usuario creado y alumno/profesor */
                return true;
            } else {
                throw new ExceptionPersonalizada("Error al crear nuevo usuario");

            }
        } catch (Exception e) {
            throw new ExceptionPersonalizada("Error al crear nuevo usuario" + e);
        }
    }


    public Usuario login(String nombreUsuario, String contrasenia) {
        Usuario usuario1 = null;
        try {
            usuario1 = jwtProvider.login(nombreUsuario, contrasenia);
            if (usuario1 != null)
                return usuario1;
            else
                throw new ExceptionPersonalizada("No se logueo correctamente. Nombre de Usuario o contrasenia mal colocadas");
        } catch (ExceptionPersonalizada e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
        return usuario1;
    }
}
