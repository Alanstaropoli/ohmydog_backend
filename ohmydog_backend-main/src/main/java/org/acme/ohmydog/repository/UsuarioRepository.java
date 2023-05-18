package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Usuario;
import org.acme.ohmydog.entities.Perro;
import java.util.List;

@ApplicationScoped
// PanacheRepository es una interfaz que brinda metodos de consultas para buscar y recuperar datos de la base de datos
public class UsuarioRepository implements PanacheRepository<Usuario> {

    /**
     * Busca por email a un usuario en la base de datos
     * @param email
     * @return Usuario
     */
    public Usuario buscarUsuarioPorEmail(String email) {
        return find("email", email).firstResult();
    }

    /**
     * Busca por id a un usuario en la base de datos
     * @param id
     * @return Usuario
     */
    public Usuario buscarUsuarioPorId(Long id) {
        return find("id", id).firstResult();
    }

    /**
     * Se encarga de crear un nuevo objeto Usuario con los datos proporcionados y persistirlo en la base de datos utilizando el metodo persist() del repositorio de
     * usuario
     * @param email
     * @param password
     * @param nombre
     * @param apellido
     * @param dni
     * @param localidad
     * @param direccion
     * @param telefono
     */
    public void register(String email, String password, String nombre, String apellido, Long dni,
                         String localidad, String direccion, Long telefono, String rol) {
        Usuario usuario = new Usuario(email, password, nombre, apellido, dni, localidad, direccion, telefono, rol); // Crea un nuevo usuario con los datos proporcionados
        persist(usuario); // Persiste el nuevo usuario en la base de datos
    }

    /**
     * Se encarga de eliminar un objeto Usuario especifico de la base de datos utilizando el método delete() del repositorio de usuario
     * @param usuario
     */
    public void eliminate(Usuario usuario) {
        delete(usuario); // Elimina el usuario de la base de datos
    }

    public List<Usuario> listarUsuarios() {
        return listAll();
    }

    public List<Perro> listarPerrosDelUsuario(Long id){
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id);
        return usuario.getListaPerros();
    }
}
