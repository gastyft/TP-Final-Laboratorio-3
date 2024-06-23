package com.tpFinal.seguridad.jwt;


import com.tpFinal.excepciones.ExceptionPersonalizada;
import com.tpFinal.repository.Repository;

import com.tpFinal.seguridad.entity.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCrypt;


import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class JwtProvider implements Serializable{

    Repository<Usuario> usuarioRepository = new Repository<>(Usuario.class);
    private final String claveSecreta = "miClaveSecretaesMauriYSofisecretsecretsecretsecretsecretsecretsecretsecretsecretsecret"; // Debería ser cargada de forma segura
  //  private final int duracionTokenEnSegundos = 86400; // 24 horas en segundos
 //   private final String archivoTokens = "tokens.txt"; // Archivo para almacenar tokens (puedes ajustar la ruta según tu necesidad)

    public String generateToken(String contrasenia) throws ExceptionPersonalizada {
     //   Usuario usuarioValidado = validarCredenciales(usuario.getNombreUsuario(), usuario.getPassword());
       // if (usuarioValidado != null) {
            // Generar el token JWT con el claim de contraseña
            return Jwts.builder()
                    .claim("password", contrasenia) // Agregar la contraseña como claim
                    .signWith(SignatureAlgorithm.HS512, claveSecreta) // Firmar con HS512 y la clave secreta
                    .compact();
     //   } else {
         //   mostrarMensajeError("Credenciales inválidas para el usuario: " + usuario.getNombreUsuario());
        //    throw new ExceptionPersonalizada("Credenciales inválidas para el usuario: " + usuario.getNombreUsuario());
      //  }
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

 /* No se utilizara esta funcion
  private void guardarTokenEnArchivo(Usuario usuario, String token) throws ExceptionPersonalizada {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTokens, true))) {
            writer.write(usuario.getNombreUsuario() + ":" + token); // Aquí puedes ajustar el formato según tu necesidad
            writer.newLine();
        } catch (IOException e) {
            throw new ExceptionPersonalizada("Error al guardar token en archivo: " + e.getMessage());
        }
    }
    */


    private void mostrarMensajeError(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        });
    }

    private Usuario validarCredenciales(String nombreUsuario, String contrasenia) throws ExceptionPersonalizada {
        Map<String, Usuario> usuarios = cargarUsuariosDesdeArchivo();
        if (usuarios.containsKey(nombreUsuario)) {
            Usuario usuario  = usuarios.get(nombreUsuario);
            String tokenGuardado = usuario.getPassword(); // El password es el token JWT almacenado
            // Generar el token JWT a partir de la contraseña ingresada
            String tokenIngresado = generateToken(contrasenia);
            // Comparar los tokens
            if (tokenIngresado.equals(tokenGuardado)) {
                return usuario; // Si los tokens son iguales, devolver el usuario
            }
        }

        return null;
    }

    private Map<String, Usuario> cargarUsuariosDesdeArchivo() throws ExceptionPersonalizada {
        try {
            Map<String, Usuario> usuarios = new HashMap<>();

            List<Usuario> usuariosDesdeRepository = usuarioRepository.listar(); // Obtener usuarios desde el Repository
            for (Usuario usuario :  usuariosDesdeRepository) {
                usuarios.put(usuario.getNombreUsuario(), usuario);
            }
            return usuarios;
        } catch (Exception e) {
            throw new ExceptionPersonalizada("Error al cargar usuarios desde archivo: " + e.getMessage());
        }
    }

    private boolean usuarioExiste(String nombreUsuario) throws ExceptionPersonalizada {
        Map<String, Usuario> usuarios = cargarUsuariosDesdeArchivo();
        return usuarios.containsKey(nombreUsuario);
    }

    public boolean registrarUsuario(Usuario usuario) throws ExceptionPersonalizada {
        if (usuarioExiste(usuario.getNombreUsuario())) {
            mostrarMensajeError("El usuario ya existe: " + usuario.getNombreUsuario());
            return false;
        }
        try {
            // Hashear la contraseña antes de guardarla
       //         String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
       //     usuario.setPassword(hashedPassword); //HASHEO CONTRASENIA
           String token= generateToken(usuario.getPassword()); // GENERO TOKEN DE SEGURIDAD (Soy inseguro)
            usuario.setPassword(token);

            usuarioRepository.agregar(usuario);
            return true;
        } catch (Exception e) {
            mostrarMensajeError("Error al registrar usuario: " + e.getMessage());
            return false;
        }

    }

    public Usuario login(String nombreUsuario, String contrasenia) throws ExceptionPersonalizada {
//FALTA TERMINAR
        Usuario usuario =validarCredenciales(nombreUsuario, contrasenia);
        if (usuario!= null) {
            return usuario;
        } else {
            throw new ExceptionPersonalizada("Credenciales inválidas para el usuario: " + nombreUsuario);
        }
    }
}