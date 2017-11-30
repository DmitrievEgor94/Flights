package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.services.core.ServiceFlight;
import com.mycompany.webapp.services.impl.ServiceFlightImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/flights")
public class FlightController implements ControllersConstants {

    private ServiceFlight serviceFlight = new ServiceFlightImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Flight flight) {

        if (flight.getId() == ABSENT_ID) {
            return Response.status(ERROR_UPDATING).entity(MESSAGE_UPDATE_ID).build();
        } else {
            boolean isUpdated = serviceFlight.update(flight);
            if (isUpdated) {
                return Response.status(SUCCESS_STATUS).entity(SUCCESS_UPDATE).build();
            } else {
                return Response.status(ERROR_UPDATING).entity(MESSAGE_FILL_FIELDS).build();
            }
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Flight flight) {


        if (flight.getId() != ABSENT_ID) {
            return Response.status(ERROR_SAVING).entity(WARNING_ID).build();
        } else {

            boolean isSaved = serviceFlight.save(flight);

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
    public Flight get(@PathParam(value = "id") long id) {
        return serviceFlight.read(id);
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        Flight flight = serviceFlight.read(id);

        if (flight == null) {
            return Response.status(ERROR_DELETE).entity(MESSAGE_DELETE_ENTITY).build();
        } else {
            serviceFlight.delete(id);
            return Response.status(SUCCESS_STATUS).entity(SUCCESS_DELETE).build();
        }
    }

    @GET
    @Path("/amount")
    public Response getNumberOfEntities() {
        return Response.status(SUCCESS_STATUS).entity(serviceFlight.getNumberOfEntities()).build();
    }

    @GET
    @Path("/{firstId}-{lastId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> getList(@PathParam(value = "firstId") long firstId, @PathParam(value = "lastId") long lastId) {
        return serviceFlight.readAll(firstId, lastId);
    }

}
