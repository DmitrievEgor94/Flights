package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;

import java.util.List;

public interface FlightDao extends CrudOperations<Flight> {

    String CLASS_NAME = "Flight";

    String JQPL_FLIGHTS_FOR_PLANE = "SELECT f FROM " + CLASS_NAME + " f join f.planes p " +
            " WHERE p.planeNumber = :planeNumber";

    List<Flight> getFlightsOfPlane(Plane plane);
}
