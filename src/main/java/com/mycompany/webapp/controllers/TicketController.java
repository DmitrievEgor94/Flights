package com.mycompany.webapp.controllers;

import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.core.ServiceTicket;
import com.mycompany.webapp.services.impl.ServiceTicketImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tickets")
public class TicketController {

    private ServiceTicket serviceTicket = new ServiceTicketImpl();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Ticket Ticket) {
        String updateMessage = serviceTicket.update(Ticket);

        if (updateMessage == null) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(updateMessage).build();
        }
    }

    @POST
    public Response save(Ticket Ticket) {
        String saveMessage = serviceTicket.save(Ticket);

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
        return Response.status(Response.Status.OK).entity(serviceTicket.read(id)).build();
    }

    @DELETE
    @Path(value = "/{id}")
    public Response delete(@PathParam(value = "id") long id) {
        if (serviceTicket.delete(id)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
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
    public Response getList(@PathParam(value = "firstId") long firstId, @PathParam(value = "lastId") long lastId) {
        return Response.status(Response.Status.OK)
                .entity(serviceTicket.readAll(firstId, lastId)).build();
    }

}
