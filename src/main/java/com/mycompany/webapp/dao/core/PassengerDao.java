package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Passenger;

import java.util.List;

public interface PassengerDao extends CrudOperations<Passenger> {

    List<Passenger> getPassengersWithSeveralTickets();
}
