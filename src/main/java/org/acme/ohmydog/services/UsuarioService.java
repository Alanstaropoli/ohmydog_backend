package org.acme.ohmydog.services;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.Turno;
//import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.repository.PerroRepository;
import org.acme.ohmydog.repository.TurnoRepository;
import org.acme.ohmydog.requests.UsuarioRequest;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped // Asegura que el objeto se inicialice solo una vez y se reutilice en toda la
                   // aplicacion
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    TurnoRepository turnoRepository;

    @Inject
    PerroRepository perroRepository;

    /**
     * Registra un nuevo usuario en la base de datos con los datos proporcionados
     * 
     * @param usuarioRequest
     * @return boolean
     */
    @Transactional
    public boolean register(UsuarioRequest usuarioRequest) {
        if (usuarioRepository.buscarUsuarioPorEmail(usuarioRequest.getEmail()) != null) { // Verificar si el email ya
                                                                                          // existe en la base de datos
            return false; // Email ya existe
        }
        usuarioRepository.register(usuarioRequest.getEmail(), usuarioRequest.getPassword(),
                usuarioRequest.getNombre(), usuarioRequest.getApellido(), usuarioRequest.getDni(),
                usuarioRequest.getLocalidad(), usuarioRequest.getDireccion(), usuarioRequest.getTelefono(),
                usuarioRequest.getRol()); // Registra el nuevo usuario en la base de datos
        return true;
    }

    /**
     * Recibe los parámetros para modificar un usuario ya existente en la base de
     * datos.
     * 
     * @param id
     * @param email
     * @param password
     * @param nombre
     * @param apellido
     * @param dni
     * @param localidad
     * @param direccion
     * @param telefono
     * @return
     */
    @Transactional
    public boolean modificarUsuario(Long id, String email, String password, String nombre, String apellido, Long dni,
            String localidad, String direccion, Long telefono, String rol) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return false; // No se encontró el usuario con el id especificado
        }
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setLocalidad(localidad);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuario.setRol(rol);
        usuarioRepository.persist(usuario); // Actualizar el usuario en la base de datos
        return true;
    }

    /**
     * El borrado ahora es logico, deja de ser fisico
     * Busca en la base de datos el usuario correspondiente al ID proporcionado como
     * parametro. Si se encuentra, se le setea su boleano borrado en true
     * 
     * @param id
     * @return
     */
    @Transactional
    public boolean eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return false; // No se encontró el usuario con el id especificado
        }
        usuario.setBorrado(true);

        usuarioRepository.persist(usuario);
        // Se "borran" los perros del usuario, poniendo sus "borrado" en true
        List<Perro> perrosUsuario = usuario.getPerros();
        for (Perro p : perrosUsuario) {
            // Si no esta borrado desde antes..
            if (!p.getBorrado()) {
                p.setBorrado(true);
                List<Turno> turnos = p.getTurnos();
                for (Turno t : turnos) {
                    if (t.getEstado().equals("Pendiente")) {
                        t.setEstado("Rechazado");
                        turnoRepository.persist(t);
                    }
                }
                perroRepository.persist(p);
            }
        }
        return true;
    }

    @Transactional
    public boolean recuperarUsuario(Long id) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return false; // No se encontró el usuario con el id especificado
        }

        List<Perro> perros = usuario.getPerros();
        for (Perro p : perros) {
            // recupera sus perros
            p.setBorrado(false);
            perroRepository.persist(p);
        }

        usuario.setBorrado(false);
        usuarioRepository.persist(usuario);
        return true;
    }

    /**
     * Recupera todos los usuarios de la base de datos cuyo "borrado" es false
     *
     * @return Lista de usuarios
     */
    @Transactional
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.listarUsuarios();
        List<Usuario> usuariosFiltrados = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (!u.getBorrado()) {
                usuariosFiltrados.add(u);
            }
        }
        return usuariosFiltrados;
    }

    /**
     * Recupera todos los usuarios de la base de datos cuyo "borrado" es true
     *
     * @return Lista de usuarios
     */
    @Transactional
    public List<Usuario> listarUsuariosBorrados() {
        List<Usuario> usuarios = usuarioRepository.listarUsuarios();
        List<Usuario> usuariosFiltrados = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u.getBorrado()) {
                usuariosFiltrados.add(u);
            }
        }
        return usuariosFiltrados;
    }

    @Transactional
    public boolean cambiarContrasena(Long id, String contrasenaVieja, String contrasenaNueva,
            String contrasenaConfirmacion) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return false; // No se encontró el usuario con el id especificado
        }
        if (!usuario.coincidePassword(contrasenaVieja)) {
            return false;
        }
        usuario.setPassword(contrasenaNueva);
        usuarioRepository.persist(usuario); // Actualizar el usuario en la base de datos
        return true;
    }

    /**
     * Retorna un usuario mediante su ID.
     * 
     * @param id
     * @return boolean
     */
    @Transactional
    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return null; // No se encontró el usuario con el id especificado
        }
        return usuario;
    }

}
