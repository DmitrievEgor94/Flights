package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.services.core.ServicePassenger;
import com.mycompany.webapp.services.impl.ServicePassengerImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/passengers")
public class PassengerController {

    private ServicePassenger servicePassenger = new ServicePassengerImpl();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Passenger passenger) {
        String updateMessage = servicePassenger.update(passenger);

        if (updateMessage == null) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(updateMessage).build();
        }
    }

    @POST
    public Response save(Passenger passenger) {
        String saveMessage = servicePassenger.save(passenger);

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
        if (servicePassenger.read(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(servicePassenger.read(id)).build();
        }
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        servicePassenger.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/amount")
    public Response getNumberOfEntities() {
        return Response.status(Response.Status.OK)
                .entity(servicePassenger.getNumberOfEntities()).build();
    }

    @GET
    @Path("/{firstId}-{lastId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@QueryParam("from") long firstId, @QueryParam("to") long lastId) {
        List<Passenger> passengers = servicePassenger.readAll(firstId, lastId);

        if (passengers.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK)
                .entity(passengers).build();
    }
}