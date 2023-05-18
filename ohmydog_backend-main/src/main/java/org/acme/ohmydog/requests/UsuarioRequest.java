package org.acme.ohmydog.requests;
import java.util.List; 
import org.acme.ohmydog.entities.Perro;

public class UsuarioRequest {
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private Long dni;
    private String localidad;
    private String direccion;
    private Long telefono;
    private String rol;
    private List<Perro> listaPerros;

    public UsuarioRequest(String email, String password, String nombre, String apellido, Long dni, String localidad,
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

    public List<Perro> getPerros(){
        return this.listaPerros;
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
}
