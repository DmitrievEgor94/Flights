package com.mycompany.webapp.services.core;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Ticket;

import java.util.List;

public interface ServiceTicket extends ServiceForCrudOperations<Ticket> {

    List<Ticket> getTicketsOfPassenger(Passenger passenger);
}
