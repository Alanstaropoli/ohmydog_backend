package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "PASEADOR")

public class Paseador extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private Long dni;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "zona")
    private String zona;

    public Paseador() {

    }

    public Paseador(String nombre, String apellido, Long dni, Long telefono, String email, String zona) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
        this.zona = zona;
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

    public Long getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getZona() {
        return this.zona;
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

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }
}
