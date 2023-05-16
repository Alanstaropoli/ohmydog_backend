package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TURNO")
public class Turno extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String perro;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "estado")
    private String estado;


    public Turno() {
    }

    public Turno(LocalDate fecha, String motivo) {
        //this.perro = perro;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = "Pendiente";
    }

//    public String getPerro() {
//        return this.perro;
//    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public String getEstado() {
        return this.estado;
    }

    //    public void setPerro(String perro) {
//        this.perro = perro;
//    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
