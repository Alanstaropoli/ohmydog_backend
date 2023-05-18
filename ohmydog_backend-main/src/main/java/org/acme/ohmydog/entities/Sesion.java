package org.acme.ohmydog.entities;


import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.Objects;

@SessionScoped
public class Sesion implements Serializable {
    private Usuario usuario;
    private String token;

    public void clear() {
        this.usuario = null;
        this.token = "";
    }

    public boolean isLoggedIn(String token) {
        return Objects.equals(this.token, token);
    }

    public boolean esVeterinario() {
        if (this.usuario == null) {
            return false;
        }
        return this.usuario.esVeterinario();
    }

    public boolean esCliente() {
        if (this.usuario == null) {
            return false;
        }
        return this.usuario.esCliente();
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public String getToken() {
        return this.token;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
