package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.services.core.ServicePassenger;
import com.mycompany.webapp.services.impl.ServicePassengerImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/passengers")
public class PassengerController implements ControllersConstants {

    private ServicePassenger servicePassenger = new ServicePassengerImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Passenger passenger) {

        if (passenger.getId() == ABSENT_ID) {
            return Response.status(ERROR_UPDATING).entity(MESSAGE_UPDATE_ID).build();
        } else {
            boolean isUpdated = servicePassenger.update(passenger);
            if (isUpdated) {
                return Response.status(SUCCESS_STATUS).entity(SUCCESS_UPDATE).build();
            } else {
                return Response.status(ERROR_UPDATING).entity(MESSAGE_FILL_FIELDS).build();
            }
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Passenger passenger) {
        if (passenger.getId() != ABSENT_ID) {
            return Response.status(ERROR_SAVING).entity(WARNING_ID).build();
        } else {

            boolean isSaved = servicePassenger.save(passenger);

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
    public Passenger get(@PathParam(value = "id") long id) {
        return servicePassenger.read(id);
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        Passenger passenger = servicePassenger.read(id);

        if (passenger == null) {
            return Response.status(ERROR_DELETE).entity(MESSAGE_DELETE_ENTITY).build();
        } else {
            servicePassenger.delete(id);
            return Response.status(SUCCESS_STATUS).entity(SUCCESS_DELETE).build();
        }
    }

    @GET
    @Path("/amount")
    public Response getNumberOfEntities() {
        return Response.status(SUCCESS_STATUS).entity(servicePassenger.getNumberOfEntities()).build();
    }

    @GET
    @Path("/{firstId}-{lastId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Passenger> getList(@PathParam(value = "firstId") long firstId, @PathParam(value = "lastId") long lastId) {
        return servicePassenger.readAll(firstId, lastId);
    }
}