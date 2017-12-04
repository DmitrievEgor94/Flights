package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Ticket;

import java.util.List;

public interface TicketDao extends CrudOperations<Ticket> {

    List<Ticket> getTicketsOfPassenger(String firstName, String lastName);
}
