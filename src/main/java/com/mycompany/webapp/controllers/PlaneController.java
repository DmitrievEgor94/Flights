package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.ServicePlane;
import com.mycompany.webapp.services.impl.ServicePlaneImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/planes")
public class PlaneController implements ControllersConstants {

    private ServicePlane servicePlane = new ServicePlaneImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Plane plane) {

        if (plane.getId() == ABSENT_ID) {
            return Response.status(ERROR_UPDATING).entity(MESSAGE_UPDATE_ID).build();
        } else {
            boolean isUpdated = servicePlane.update(plane);
            if (isUpdated) {
                return Response.status(SUCCESS_STATUS).entity(SUCCESS_UPDATE).build();
            } else {
                return Response.status(ERROR_UPDATING).entity(MESSAGE_FILL_FIELDS).build();
            }
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Plane plane) {
        if (plane.getId() != ABSENT_ID) {
            return Response.status(ERROR_SAVING).entity(WARNING_ID).build();
        } else {

            boolean isSaved = servicePlane.save(plane);

            if (isSaved) {
                return Response.status(SUCCESS_STATUS).entity(SUCCESS_SAVING).build();
            } else {
                return Response.status(ERROR_SAVING).entity(MESSAGE_FILL_FIELDS).build();
            }
        }
    }

    @GET
    @Path(value = "/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Plane get(@PathParam(value = "id") long id) {
        return servicePlane.read(id);
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        Plane plane = servicePlane.read(id);

        if (plane == null) {
            return Response.status(ERROR_DELETE).entity(MESSAGE_DELETE_ENTITY).build();
        } else {
            servicePlane.delete(id);
            return Response.status(SUCCESS_STATUS).entity(SUCCESS_DELETE).build();
        }
    }

    @GET
    @Path("/amount")
    public Response getNumberOfEntities() {
        return Response.status(SUCCESS_STATUS).entity(servicePlane.getNumberOfEntities()).build();
    }

    @GET
    @Path("/{firstId}-{lastId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Plane> getList(@PathParam(value = "firstId") long firstId, @PathParam(value = "lastId") long lastId) {
        return servicePlane.readAll(firstId, lastId);
    }
}
