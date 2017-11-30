package com.mycompany.webapp.services.core;

import com.mycompany.webapp.models.Passenger;

import java.util.List;

public interface ServicePassenger extends ServiceForCrudOperations<Passenger> {

    List<Passenger> getPassengersWithSeveralTickets();
}
