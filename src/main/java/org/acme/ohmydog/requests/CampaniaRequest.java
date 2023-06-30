package org.acme.ohmydog.requests;

import java.time.LocalDate;

public class CampaniaRequest {
    private String nombre;
    private String motivo;
    private long cvu;
    private long telefono;
    private String email;
    private boolean borrado;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public CampaniaRequest(String nombre, String motivo, long cvu, long telefono, String email, LocalDate fechaInicio,
            LocalDate fechaFin) {
        this.nombre = nombre;
        this.motivo = motivo;
        this.cvu = cvu;
        this.telefono = telefono;
        this.email = email;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public long getCvu() {
        return this.cvu;
    }

    public long getTelefono() {
        return this.telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isBorrado() {
        return this.borrado;
    }

    public LocalDate getFechaInicio() {
        return this.fechaInicio;
    }

    public LocalDate getFechaFin() {
        return this.fechaFin;
    }
}
