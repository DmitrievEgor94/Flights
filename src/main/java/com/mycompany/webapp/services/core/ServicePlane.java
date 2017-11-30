package com.mycompany.webapp.services.core;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Plane;

import java.util.List;

public interface ServicePlane extends ServiceForCrudOperations<Plane> {

    List<Plane> getPlanesForPassenger(Passenger passenger);
}
