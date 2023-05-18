package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import org.acme.ohmydog.entities.Sesion;
import org.acme.ohmydog.requests.LoginRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.services.AuthService;
import javax.naming.AuthenticationException;

@Path("/auth") // Indica la ruta base del endpoint, en este caso /auth. Este valor va a concatenarse con la ruta especificada en el siguiente @Path
public class AuthController {

    @Inject
    AuthService authService;

    /**
     * Recibe una solicitud de inicio de sesion (LoginRequest) que contiene las credenciales del usuario (correo electronico y contraseña). Luego, llama a un
     * servicio (authService) para autenticar al usuario utilizando las credenciales proporcionadas. Si la autenticacion es exitosa, establece la sesion
     * del usuario y devuelve una respuesta de estado HTTP 200 (OK). Si la autenticacion falla, devuelve una respuesta de estado HTTP 401 (Unauthorized)
     *
     * @param loginRequest
     * @return Response
     */
    @POST // Indica que el metodo HTTP que se utilizara para esta solicitud es POST. La solicitud va a crear un nuevo recurso (en este caso, una sesion de autenticacion).
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON) // Indica que el tipo de contenido que se producira como respuesta sera JSON
    @Consumes(MediaType.APPLICATION_JSON) // Indica que el tipo de contenido que se espera como entrada es JSON
    public Response login(LoginRequest loginRequest) {
        try {
            Sesion sesion = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return Response.ok().entity(sesion).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     * Llama a un servicio (authService) para borrar la sesion actual. Luego, devuelve una respuesta de estado HTTP 200(OK) con un mensaje que indica que la sesion
     * se ha cerrado exitosamente
     * @return Response
     */
    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logout(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token)) {
            authService.logout();
            return Response.ok("Sesión cerrada exitosamente").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
