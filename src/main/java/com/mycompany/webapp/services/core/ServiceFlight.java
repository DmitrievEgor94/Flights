package com.mycompany.webapp.services.core;

import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;

import java.util.List;

public interface ServiceFlight extends ServiceForCrudOperations<Flight> {

    List<Flight> getFlightsOfPlane(Plane plane);
}
