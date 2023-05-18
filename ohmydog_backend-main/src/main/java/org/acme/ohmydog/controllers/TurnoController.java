package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.ohmydog.requests.TurnoRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.TurnoService;

@Path("/turnos")
public class TurnoController {

    @Inject
    TurnoService turnoService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, TurnoRequest turnoRequest) {
        if (authService.isLoggedIn(token)) {
            if (turnoService.register(turnoRequest)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarTurnos(@HeaderParam("token") String token) {
        if (authService.isLoggedIn(token)) {
            return Response.ok(turnoService.listarTurnos()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
