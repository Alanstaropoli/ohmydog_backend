package org.acme.ohmydog.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import org.acme.ohmydog.requests.CampaniaRequest;
import org.acme.ohmydog.services.AuthService;
import org.acme.ohmydog.services.CampaniaService;

@Path("/campanias")
public class CampaniaController {

    @Inject
    CampaniaService campaniaService;

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@HeaderParam("token") String token, CampaniaRequest campaniaRequest) {
        if ((authService.isLoggedIn(token) && (authService.esVeterinario()))) {
            if (campaniaService.register(campaniaRequest)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarCampanias() {
        return Response.ok(campaniaService.listarCampanias()).build();
    }

    @GET
    @Path("/borradas")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listarCampaniasBorradas() {
        return Response.ok(campaniaService.listarCampaniasBorradas()).build();
    }

    @PUT
    @Path("/modify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarCampania(@HeaderParam("token") String token, @PathParam("id") Long id,
            CampaniaRequest campaniaRequest) {
        if ((authService.isLoggedIn(token)) && (authService.esVeterinario())) {
            if (campaniaService.modificarCampania(id, campaniaRequest.getNombre(), campaniaRequest.getMotivo(),
                    campaniaRequest.getTelefono(), campaniaRequest.getCvu(), campaniaRequest.getEmail(),
                    campaniaRequest.getFechaInicio(), campaniaRequest.getFechaFin())) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    // Endpoint para recuperar las campanias borradas
    @PUT
    @Path("/recover/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response recuperarCampania(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if ((authService.isLoggedIn(token)) && (authService.esVeterinario())) {
            if (campaniaService.recuperarCampania(id)) {
                return Response.ok().build();
            } else {
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE // Indica que se esta realizando una operacion de eliminacion
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarCampania(@HeaderParam("token") String token, @PathParam("id") Long id) {
        if (authService.isLoggedIn(token) && (authService.esVeterinario())) {
            if (campaniaService.eliminarCampania(id)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Retorna una campania mediante el ID que se pasa por URL
     * 
     * 
     * @return Response
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCampaniaPorId(@HeaderParam("token") String token, @PathParam("id") Long id) {
        return Response.ok(campaniaService.buscarCampaniaPorId(id)).build();
    }
}
