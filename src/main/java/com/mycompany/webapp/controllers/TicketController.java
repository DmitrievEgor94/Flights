package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.core.ServiceTicket;
import com.mycompany.webapp.services.impl.ServiceTicketImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tickets")
public class TicketController implements ControllersConstants {

    private ServiceTicket serviceTicket = new ServiceTicketImpl();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Ticket ticket) {

        if (ticket.getId() == ABSENT_ID) {
            return Response.status(ERROR_UPDATING).entity(MESSAGE_UPDATE_ID).build();
        } else {
            boolean isUpdated = serviceTicket.update(ticket);
            if (isUpdated) {
                return Response.status(SUCCESS_STATUS).entity(SUCCESS_UPDATE).build();
            } else {
                return Response.status(ERROR_UPDATING).entity(MESSAGE_FILL_FIELDS).build();
            }
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Ticket ticket) {

        if (ticket.getId() != ABSENT_ID) {
            return Response.status(ERROR_SAVING).entity(WARNING_ID).build();
        } else {

            boolean isSaved = serviceTicket.save(ticket);

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
    public Ticket get(@PathParam(value = "id") long id) {
        return serviceTicket.read(id);
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        Ticket ticket = serviceTicket.read(id);

        if (ticket == null) {
            return Response.status(ERROR_DELETE).entity(MESSAGE_DELETE_ENTITY).build();
        } else {
            serviceTicket.delete(id);
            return Response.status(SUCCESS_STATUS).entity(SUCCESS_DELETE).build();
        }
    }

    @GET
    @Path("/amount")
    public Response getNumberOfEntities() {
        return Response.status(SUCCESS_STATUS).entity(serviceTicket.getNumberOfEntities()).build();
    }

    @GET
    @Path("/{firstId}-{lastId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ticket> getList(@PathParam(value = "firstId") long firstId, @PathParam(value = "lastId") long lastId) {
        return serviceTicket.readAll(firstId, lastId);
    }

}
