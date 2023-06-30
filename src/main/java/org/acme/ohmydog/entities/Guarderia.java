package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "GUARDERIA")

public class Guarderia extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "direccion")
    private String direccion;

    public Guarderia() {

    }

    public Guarderia(String nombre, String disponibilidad, Long telefono, String email, String localidad,
            String direccion) {
        this.nombre = nombre;
        this.disponibilidad = disponibilidad;
        this.telefono = telefono;
        this.email = email;
        this.localidad = localidad;
        this.direccion = direccion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDisponibilidad() {
        return this.disponibilidad;
    }

    public Long getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
