package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "CAMPANIA")
public class Campania extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "cvu")
    private Long cvu;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "email")
    private String email;
    @Column(name = "borrado")
    private boolean borrado;

    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    public Campania() {
    }

    public Campania(String nombre, String motivo, long cvu, long telefono, String email, LocalDate fechaInicio,
            LocalDate fechaFin) {
        this.nombre = nombre;
        this.motivo = motivo;
        this.cvu = cvu;
        this.telefono = telefono;
        this.email = email;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.borrado = false;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public long getCvu() {
        return this.cvu;
    }

    public void setCvu(long cvu) {
        this.cvu = cvu;
    }

    public long getTelefono() {
        return this.telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isBorrado() {
        return this.borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

}
