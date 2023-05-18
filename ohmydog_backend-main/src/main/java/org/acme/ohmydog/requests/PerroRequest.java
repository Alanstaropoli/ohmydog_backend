package org.acme.ohmydog.requests;

public class PerroRequest {
    private String nombre;
    private String raza;
    private int edad;
    private String enfermedad;
    private char sexo;
    private String caracteristicas;
    private Long idUsuario; // El ID del usuario que es dueño del perro

    public PerroRequest(String nombre, String raza, int edad, String enfermedad, 
        char sexo, String caracteristicas, Long idUsuario) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.enfermedad = enfermedad;
        this.sexo = sexo;
        this.caracteristicas = caracteristicas;
        this.idUsuario = idUsuario;
    }
    
    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getRaza() {
        return raza;
    }
    public int getEdad() {
        return edad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public char getSexo() {
        return sexo;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }
    
}
