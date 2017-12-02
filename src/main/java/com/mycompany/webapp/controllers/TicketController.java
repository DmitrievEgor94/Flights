package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.core.ServiceTicket;
import com.mycompany.webapp.services.impl.ServiceTicketImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tickets")
public class TicketController {

    private ServiceTicket serviceTicket = new ServiceTicketImpl();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Ticket ticket) {
        String updateMessage = serviceTicket.update(ticket);

        if (updateMessage == null) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(updateMessage).build();
        }
    }

    @POST
    public Response save(Ticket ticket) {
        String saveMessage = serviceTicket.save(ticket);

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
        if (serviceTicket.read(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(serviceTicket.read(id)).build();
        }
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        serviceTicket.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/amount")
    public Response getNumberOfEntities() {
        return Response.status(Response.Status.OK)
                .entity(serviceTicket.getNumberOfEntities()).build();
    }

    @GET
    @Path("/{firstId}-{lastId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@QueryParam("from") long firstId, @QueryParam("to") long lastId) {
        List<Ticket> tickets = serviceTicket.readAll(firstId, lastId);

        if (tickets.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK)
                .entity(tickets).build();
    }
}
