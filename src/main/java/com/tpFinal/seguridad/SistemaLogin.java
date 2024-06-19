package com.tpFinal.seguridad;

import com.tpFinal.entidades.Alumno;
import com.tpFinal.entidades.Profesor;
import com.tpFinal.enumeraciones.RolNombre;
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


    public void crearUsuario(String nombre, String nombreUsuario, String email, String password, Profesor per) throws ExceptionPersonalizada {

        try {
            Rol rol = new Rol(RolNombre.ROL_PROFESOR);
            Usuario usuario = new Usuario(nombre, nombreUsuario, email, password,rol,per);
            if( jwtProvider.registrarUsuario(usuario)) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente", "APP", JOptionPane.OK_OPTION);
                });
            }
            else throw new  ExceptionPersonalizada("Error al crear nuevo usuario");
        } catch (Exception e) {
            throw new ExceptionPersonalizada("Error al crear nuevo usuario" + e);
        }
    }
    public void crearUsuario(String nombre, String nombreUsuario, String email, String password, Alumno per) throws ExceptionPersonalizada {

        try {
            Rol rol = new Rol(RolNombre.ROL_ALUMNO);
            Usuario usuario = new Usuario(nombre, nombreUsuario, email, password, rol,per);
            if( jwtProvider.registrarUsuario(usuario)) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente", "Error", JOptionPane.OK_OPTION);
                });
            }
            else throw new  ExceptionPersonalizada("Error al crear nuevo usuario");
        } catch (Exception e) {
            throw new ExceptionPersonalizada("Error al crear nuevo usuario"+e);
        }
    }

    public Usuario login(String nombreUsuario,String contrasenia)
    {
        Usuario usuario1 =null;
        try {
            usuario1 = jwtProvider.login(nombreUsuario, contrasenia);
            if(usuario1!=null)
                return usuario1;
            else throw new ExceptionPersonalizada("No se logueo correctamente. Nombre de Usuario o contrasenia mal colocadas");


        } catch (ExceptionPersonalizada e) {
            //MENSAJE SWING
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Login", JOptionPane.ERROR_MESSAGE);

        }
        return usuario1;
    }
}
