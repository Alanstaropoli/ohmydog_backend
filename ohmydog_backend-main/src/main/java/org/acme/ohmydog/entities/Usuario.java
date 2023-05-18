package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity // Indicia que la clase Usuario es una entidad, por lo que esta asociada a una tabla en la base de datos
@Table(name = "usuarios")
public class Usuario extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "contraseña")
    private String password;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni", unique = true)
    private Long dni;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "rol")
    private String rol;

    // Define una relacion "uno a muchos" con el usuario y sus perros
    // No hace falta iniciarlizarla porque quarkus se encarga de ello.
    @OneToMany(cascade = CascadeType.ALL) 
    private List<Perro> listaPerros;

    public Usuario() {
    }

    public Usuario(String email, String password, String nombre, String apellido, Long dni, String localidad,
                   String direccion, Long telefono, String rol) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.localidad = localidad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
    }

    public boolean esVeterinario() { return Objects.equals(this.rol, "veterinario"); }

    public boolean esCliente() { return Objects.equals(this.rol, "cliente"); }

    public List<Perro> getListaPerros(){
        return this.listaPerros;
    }

    // Funcionalidades extra para administrar los perros
    public Perro getPerroPorNombre(String nombre){
        for (Perro perro : this.listaPerros) {
            if (perro.getNombre().equals(nombre)) 
                // El nombre coincide, existe un perro con ese nombre
                return perro;
        }
        return null;
    }

    public boolean eliminarPerro(Perro perro){
        return this.listaPerros.remove(perro);
    }
    // Fin de funcionalidades extra para administrar los perros

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public Long getDni() {
        return this.dni;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public String getRol() {
        return this.rol;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void agregarPerro(Perro perro){
        this.listaPerros.add(listaPerros.size(), perro); // Lo agrega al final de la lista
    }
}
