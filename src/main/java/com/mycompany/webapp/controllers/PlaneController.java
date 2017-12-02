package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.ServicePlane;
import com.mycompany.webapp.services.impl.ServicePlaneImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/planes")
public class PlaneController {

    private ServicePlane servicePlane = new ServicePlaneImpl();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Plane plane) {
        String updateMessage = servicePlane.update(plane);

        if (updateMessage == null) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(updateMessage).build();
        }
    }

    @POST
    public Response save(Plane plane) {
        String saveMessage = servicePlane.save(plane);

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
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@QueryParam("from") long firstId, @QueryParam("to") long lastId) {
        List<Plane> planes = servicePlane.readAll(firstId, lastId);

        if (planes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK)
                .entity(planes).build();
    }
}
