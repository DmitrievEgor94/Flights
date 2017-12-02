package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.ServicePlane;
import com.mycompany.webapp.services.impl.ServicePlaneImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/planes")
public class PlaneController {

    private ServicePlane servicePlane = new ServicePlaneImpl();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Plane Plane) {
        String updateMessage = servicePlane.update(Plane);

        if (updateMessage == null) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(updateMessage).build();
        }
    }

    @POST
    public Response save(Plane Plane) {
        String saveMessage = servicePlane.save(Plane);

        if (saveMessage == null) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(saveMessage).build();
        }
    }

    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam(value = "id") long id) {
        if (servicePlane.read(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(servicePlane.read(id)).build();
        }
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        servicePlane.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/amount")
    public Response getNumberOfEntities() {
        return Response.status(Response.Status.OK)
                .entity(servicePlane.getNumberOfEntities()).build();
    }

    @GET
    @Path("/{firstId}-{lastId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@PathParam(value = "firstId") long firstId, @PathParam(value = "lastId") long lastId) {
        return Response.status(Response.Status.OK)
                .entity(servicePlane.readAll(firstId, lastId)).build();
    }
}
