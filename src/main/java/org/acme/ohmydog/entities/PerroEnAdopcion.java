package org.acme.ohmydog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "PERROENADOPCION")

public class PerroEnAdopcion extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "idUsuario")
    private Long idUsuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "raza")
    private String raza;
    @Column(name = "edad")
    private int edad;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private Long telefono;
    @Column(name = "estado")
    private String estado;
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @Column(name = "enfermedades")
    private String enfermedades;
    

    public PerroEnAdopcion() {

    }

    public PerroEnAdopcion(Long idUsuario, String nombre, String raza, int edad, String sexo, String email, Long telefono, String caracteristicas, String enfermedades) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.sexo = sexo;
        this.email = email;
        this.telefono = telefono;
        this.caracteristicas = caracteristicas;
        this.enfermedades = enfermedades;
        this.estado = "Pendiente";
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public Long getIdUsuario(){
        return this.idUsuario;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getRaza() {
        return this.raza;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public Long getTelefono() {
        return telefono;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public String getEnfermedades() {
        return enfermedades;
    }
}
