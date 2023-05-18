package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.ohmydog.requests.PerroRequest;
import org.acme.ohmydog.services.PerroService;
import org.acme.ohmydog.services.AuthService;

@Path("/misperros")
public class PerroController {

    @Inject // Indica a Quarkus que proporcione automaticamente el objeto en la app en tiempo de ejecucion
    PerroService perroService;
    
    @Inject
    AuthService authService;

    /**
     * El metodo recibe un objeto PerroRequest que contiene los datos del nuevo perro. 
     * Luego, el método llama al servicio del perro para registrar el perro correspondiente en la base
     * de datos y devuelve una respuesta HTTP confirmando el exito o no de la operacion.
     * @param perroRequest
     * @return Response
     */
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, PerroRequest perroRequest) {
        if (authService.isLoggedIn(token)){ // Valida sesion activa
            perroService.register(perroRequest);
            return Response.ok().build();
        }
         return Response.status(Response.Status.BAD_REQUEST).build(); // La operacion fallo
    }

}

