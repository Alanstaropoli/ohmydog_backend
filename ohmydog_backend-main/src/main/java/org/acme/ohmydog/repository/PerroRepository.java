
package org.acme.ohmydog.repository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Perro;
import org.acme.ohmydog.entities.Usuario;

@ApplicationScoped
// PanacheRepository es una interfaz que brinda metodos de consultas para buscar y recuperar datos de la base de datos
public class PerroRepository implements PanacheRepository<Perro> {

    /**
     * Busca un perro por su id
     * @param id
     * @return Perro
     */
    public Perro buscarPerroPorID(Long id) {
        return find("id", id).firstResult();
    }

    /**
     * Se encarga de crear un nuevo objeto Perro con los datos proporcionados y persistirlo en la base de datos utilizando el metodo persist() del repositorio de
     * perro
     * String nombre, String raza, int edad, String enfermedad, 
        char sexo, String caracteristicas
     * @param nombre
     * @param raza
     * @param edad
     * @param enfermedad
     * @param sexo
     * @param caracteristicas
     */
    public void register(Usuario usuario, String nombre, String raza, int edad, String enfermedad, char sexo, String caracteristicas) {
        Perro perro = new Perro(nombre, raza, edad, enfermedad, sexo, caracteristicas); // Crea un nuevo perro con los datos proporcionados
        persist(perro); // Persiste el nuevo perro en la base de datos
        usuario.agregarPerro(perro); // Lo agrega a los perros del usuario
    }

    /**
     * Se encarga de eliminar un objeto Perro especifico de la base de datos utilizando el método delete() del repositorio de perro
     * @param perro
     */
    public void eliminate(Perro perro) {
        delete(perro); // Elimina el perro de la BD
    }
}

