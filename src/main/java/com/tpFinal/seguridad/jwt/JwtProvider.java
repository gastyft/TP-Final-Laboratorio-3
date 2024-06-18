package com.tpFinal.seguridad.jwt;

import com.tpFinal.entidades.Persona;
import com.tpFinal.entidades.Profesor;
import com.tpFinal.enumeraciones.RolNombre;
import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.repository.Repository;
import com.tpFinal.seguridad.entity.Rol;
import com.tpFinal.seguridad.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCrypt;


import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class JwtProvider {

    Repository<Usuario> usuarioRepository = new Repository<>(Usuario.class);
    private final String claveSecreta = "miClaveSecretaMauriYSofi"; // Debería ser cargada de forma segura
    private final int duracionTokenEnSegundos = 86400; // 24 horas en segundos
    private final String archivoTokens = "tokens.txt"; // Archivo para almacenar tokens (puedes ajustar la ruta según tu necesidad)

    public String generateToken(Usuario usuario) throws ExceptionPersonalizada {
        Date fechaExpiracion = new Date(new Date().getTime() + duracionTokenEnSegundos * 1000);

        if (validarCredenciales(usuario.getNombreUsuario(), usuario.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(usuario.getNombreUsuario())
                    .claim("password", usuario.getPassword()) // Solo incluir la contraseña
                    .setIssuedAt(new Date())
                    .setExpiration(fechaExpiracion)
                    .signWith(SignatureAlgorithm.HS512, claveSecreta)
                    .compact();

            // Guardar el token asociado al usuario en el archivo
            guardarTokenEnArchivo(usuario, token);

            return token;
        } else {
            mostrarMensajeError("Credenciales inválidas para el usuario: " + usuario.getNombreUsuario());
            throw new ExceptionPersonalizada("Credenciales inválidas para el usuario: " + usuario.getNombreUsuario());
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(claveSecreta).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            mostrarMensajeError("Token inválido: " + e.getMessage());
            return false;
        }
    }

    private void guardarTokenEnArchivo(Usuario usuario, String token) throws ExceptionPersonalizada {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTokens, true))) {
            writer.write(usuario.getNombreUsuario() + ":" + token); // Aquí puedes ajustar el formato según tu necesidad
            writer.newLine();
        } catch (IOException e) {
            throw new ExceptionPersonalizada("Error al guardar token en archivo: " + e.getMessage());
        }
    }

    private void mostrarMensajeError(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    private boolean validarCredenciales(String nombreUsuario, String contrasenia) throws ExceptionPersonalizada {
        Map<String, String> usuarios = cargarUsuariosDesdeArchivo();

        if (usuarios.containsKey(nombreUsuario)) {
            String contraseniaGuardada = usuarios.get(nombreUsuario);
            return BCrypt.checkpw(contrasenia, contraseniaGuardada);
        }

        return false;
    }

    private Map<String, String> cargarUsuariosDesdeArchivo() throws ExceptionPersonalizada {
        try {
            Map<String, String> usuarios = new HashMap<>();
            List<Usuario> usuarioList = usuarioRepository.listar();
            for (Usuario usuario : usuarioList) {
                usuarios.put(usuario.getNombreUsuario(), usuario.getPassword());
            }
            return usuarios;
        } catch (Exception e) {
            throw new ExceptionPersonalizada("Error al cargar usuarios desde archivo: " + e.getMessage());
        }
    }

    private boolean usuarioExiste(String nombreUsuario) throws ExceptionPersonalizada {
        Map<String, String> usuarios = cargarUsuariosDesdeArchivo();
        return usuarios.containsKey(nombreUsuario);
    }

    public boolean registrarUsuario(Usuario usuario) throws ExceptionPersonalizada {
        if (usuarioExiste(usuario.getNombreUsuario())) {
            mostrarMensajeError("El usuario ya existe: " + usuario.getNombreUsuario());
            return false;
        }
        try {
            // Hashear la contraseña antes de guardarla
            String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
            usuario.setPassword(hashedPassword);
            usuarioRepository.agregar(usuario);
        } catch (Exception e) {
            mostrarMensajeError("Error al registrar usuario: " + e.getMessage());
            return false;
        }
        return true;
    }

    public Usuario login(String nombreUsuario, String contrasenia) throws ExceptionPersonalizada {
//FALTA TERMINAR
        Persona pers = new Profesor("awd","awd","awd","awd");
        Rol rol= new Rol(RolNombre.ROL_PROFESOR);
        Usuario usuario = new Usuario("wad","awd","wad","242",rol,pers);
        if (validarCredenciales(nombreUsuario, contrasenia)) {
            return usuario;
        } else {
            throw new ExceptionPersonalizada("Credenciales inválidas para el usuario: " + nombreUsuario);
        }
    }
}