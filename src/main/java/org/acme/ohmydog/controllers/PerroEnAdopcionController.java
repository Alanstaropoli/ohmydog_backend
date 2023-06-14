package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.ohmydog.requests.PerroEnAdopcionRequest;
import org.acme.ohmydog.services.PerroEnAdopcionService;
import org.acme.ohmydog.services.AuthService;

@Path("/adopciones")
public class PerroEnAdopcionController {

    @Inject
    PerroEnAdopcionService perroEnAdopcionService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(PerroEnAdopcionRequest perroEnAdopcionRequest) {
        if (perroEnAdopcionService.register(perroEnAdopcionRequest)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarPerro(@PathParam("id") Long id) {
        if (perroEnAdopcionService.eliminarPerro(id)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @PUT
    @Path("/modify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarEstadoPerro(@PathParam("id") Long id) {
        if (perroEnAdopcionService.modificarEstadoPerro(id)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarPerrosEnAdopcion() {
        return Response.ok(perroEnAdopcionService.listarPerrosEnAdopcion()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPerroPorId(@PathParam("id") Long id) {
        return Response.ok(perroEnAdopcionService.getPerroPorId(id)).build();
    }
}
