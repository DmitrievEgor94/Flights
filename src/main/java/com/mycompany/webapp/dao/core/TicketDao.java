package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Ticket;

import java.util.List;

public interface TicketDao extends CrudOperations<Ticket> {

    String PASSENGER_ENTITY_FIELD = "passenger";
    String ID_FIELD_NAME = "id";
    String DATE_FIELD_NAME = "date";

    List<Ticket> getTicketsOfPassenger(Passenger passenger);
}
