package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Passenger;

import java.util.List;

public interface PassengerDao extends CrudOperations<Passenger> {

    String NAMED_QUERY_FIND_PASSENGER_WITH_SEVERAL_TICKETS = "findPassengersWithSeveralTickets";

    List<Passenger> getPassengersWithSeveralTickets();
}
