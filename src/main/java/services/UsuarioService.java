package services;

import entities.Usuario;
import jakarta.transaction.Transactional;
import repository.UsuarioRepository;
import com.oracle.svm.core.annotate.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import requests.UsuarioRequest;

@ApplicationScoped // Asegura que el objeto se inicialice solo una vez y se reutilice en toda la aplicacion
public class UsuarioService {

    @Inject
    private UsuarioRepository usuarioRepository;

    /**
     * Registra un nuevo usuario en la base de datos con los datos proporcionados
     * @param usuarioRequest
     * @return boolean
     */
    public boolean register(UsuarioRequest usuarioRequest) {
        if (usuarioRepository.buscarUsuarioPorEmail(usuarioRequest.getEmail()) != null) { // Verificar si el email ya existe en la base de datos
            return false; // Email ya existe
        }
        usuarioRepository.register(usuarioRequest.getEmail(), usuarioRequest.getContrasena(),
                usuarioRequest.getNombre(), usuarioRequest.getApellido(), usuarioRequest.getDni(),
                usuarioRequest.getLocalidad(), usuarioRequest.getDireccion(), usuarioRequest.getTelefono()); // Registra el nuevo usuario en la base de datos
        return true;
    }

    /**
     * Recibe los parámetros para modificar un usuario ya existente en la base de datos.
     * @param id
     * @param email
     * @param contrasena
     * @param nombre
     * @param apellido
     * @param dni
     * @param localidad
     * @param direccion
     * @param telefono
     * @return
     */
    @Transactional
    public boolean modificarUsuario(Long id, String email, String contrasena, String nombre, String apellido, Long dni,
                                    String localidad, String direccion, Long telefono) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return false; // No se encontró el usuario con el id especificado
        }
        usuario.setEmail(email);
        usuario.setContrasena(contrasena);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setLocalidad(localidad);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);
        usuarioRepository.persist(usuario); // Actualizar el usuario en la base de datos
        return true;
    }

    /**
     * Busca en la base de datos el usuario correspondiente al ID proporcionado como parametro. Si se encuentra, se llama al metodo "eliminate" del
     * "usuarioRepository" para eliminarlo de la base de datos.
     * @param id
     * @return
     */
    @Transactional
    public boolean eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        if (usuario == null) {
            return false; // No se encontró el usuario con el id especificado
        }
        usuarioRepository.eliminate(usuario); // Eliminar el usuario de la base de datos
        return true;
    }

}
