package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;

import java.util.List;

public interface FlightDao extends CrudOperations<Flight> {

    List<Flight> getFlightsOfPlane(Plane plane);
}
