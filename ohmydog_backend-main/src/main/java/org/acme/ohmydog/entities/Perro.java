package org.acme.ohmydog.entities;

import jakarta.persistence.*;

@Entity // Indica que "Perro" es una entidad, asociada a la tabla:
@Table(name = "PERRO") // de la base de datos

public class Perro {
    // Se registra nombre, raza, edad, enfermedad, sexo, edad, características y dni del duenio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "raza")
    private String raza;
    @Column(name = "edad")
    private int edad;
    @Column(name = "enfermedad")
    private String enfermedad;
    @Column(name = "Sexo")
    private char sexo;
    @Column(name = "caracteristicas")
    private String caracteristicas;

    public Perro(){
    }

    public Perro(String nombre, String raza, int edad, String enfermedad, 
        char sexo, String caracteristicas) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.enfermedad = enfermedad;
        this.sexo = sexo;
        this.caracteristicas = caracteristicas;
    }

    // Getter y Setter para 'id'
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para 'nombre'
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para 'raza'
    public String getRaza() {
        return raza;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }

    // Getter y Setter para 'edad'
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Getter y Setter para 'enfermedad'
    public String getEnfermedad() {
        return enfermedad;
    }
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    // Getter y Setter para 'sexo'
    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    // Getter y Setter para 'caracteristicas'
    public String getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
}
