package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.services.core.ServiceFlight;
import com.mycompany.webapp.services.impl.ServiceFlightImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/flights")
public class FlightController {

    private ServiceFlight serviceFlight = new ServiceFlightImpl();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Flight flight) {
        String updateMessage = serviceFlight.update(flight);

        if (updateMessage == null) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(updateMessage).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Flight flight) {
        String saveMessage = serviceFlight.save(flight);

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
        return Response.status(Response.Status.OK).entity(serviceFlight.read(id)).build();
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        if (serviceFlight.delete(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Path("/amount")
    public Response getNumberOfEntities() {
        return Response.status(Response.Status.OK)
                .entity(serviceFlight.getNumberOfEntities()).build();
    }

    @GET
    @Path("/{firstId}-{lastId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@PathParam(value = "firstId") long firstId, @PathParam(value = "lastId") long lastId) {
        return Response.status(Response.Status.OK)
                .entity(serviceFlight.readAll(firstId, lastId)).build();
    }
}
