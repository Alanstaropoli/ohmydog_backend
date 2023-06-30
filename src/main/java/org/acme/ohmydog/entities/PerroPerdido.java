package org.acme.ohmydog.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PERROPERDIDO")

public class PerroPerdido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "raza")
    private String raza;
    @Column(name = "zona")
    private String zona;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "email")
    private String email;
    @Column(name = "estado")
    private String estado;

    public PerroPerdido() {
    }

    public PerroPerdido(String nombre, String raza, String zona, LocalDate fecha, String email) {
        this.nombre = nombre;
        this.raza = raza;
        this.zona = zona;
        this.fecha = fecha;
        this.email = email;
        this.estado = "Pendiente";
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getRaza() {
        return this.raza;
    }

    public String getZona() {
        return this.zona;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public String getEmail() {
        return this.email;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
