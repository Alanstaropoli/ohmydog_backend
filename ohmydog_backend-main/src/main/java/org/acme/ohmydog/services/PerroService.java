package org.acme.ohmydog.services;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.repository.PerroRepository;
import org.acme.ohmydog.repository.UsuarioRepository;
import org.acme.ohmydog.requests.PerroRequest;
import org.acme.ohmydog.entities.Usuario;

@ApplicationScoped // Asegura que el objeto se inicialice solo una vez y se reutilice en toda la aplicacion
public class PerroService {

    @Inject
    PerroRepository perroRepository;

    /**
     * Registra un nuevo perro en la base de datos con los datos proporcionados
     * Tomo la decision de que un usuario no puede tener perros con el mismo nombre (por el momento)
     * @param perroRequest
     * @return boolean
     */
    @Transactional
    public boolean register(PerroRequest perroRequest) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(perroRequest.getIdUsuario());
        if (usuario.getPerroPorNombre(perroRequest.getNombre()) != null) // Verifica si el usuario tiene un perro con ese nombre
            return false;
        perroRepository.register(usuario, perroRequest.getNombre(), perroRequest.getRaza(), perroRequest.getEdad(), perroRequest.getEnfermedad(), perroRequest.getSexo(), perroRequest.getCaracteristicas());
        return true;
    }

    /**
     * Recibe los parámetros para modificar un perro ya existente en la base de datos.
     * @param nombre
     * @param raza
     * @param edad
     * @param enfermedad
     * @param sexo
     * @param caracteristicas
     * @return boolean
     */

    @Transactional
    public boolean modificarPerro(Long id, String nombre, String raza, int edad, String enfermedad, 
    char sexo, String caracteristicas) {
        Perro perro = perroRepository.buscarPerroPorID(id);
        if (perro == null) {
            return false; // No se encontró el perro especificado
        }
        perro.setNombre(nombre);
        perro.setRaza(raza);
        perro.setEdad(edad);
        perro.setEnfermedad(enfermedad);
        perro.setSexo(sexo);
        perro.setCaracteristicas(caracteristicas);
        perroRepository.persist(perro); // Actualizar el perro en la base de datos
        return true;
    }

    /**
     * Busca en la base de datos el perro correspondiente. Si se encuentra, se llama al metodo "eliminate" del
     * "perroRepository" para eliminarlo de la base de datos.
     * @param id
     * @return boolean
     */
    @Transactional
    public boolean eliminarPerro(Long id, Usuario usuario) {
        Perro perro = perroRepository.buscarPerroPorID(id);
        if (perro == null) {
            return false; // No se encontró el perro
        }
        perroRepository.eliminate(perro); // Eliminar el perro de la base de datos
        return true;
    }

}

