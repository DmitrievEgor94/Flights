package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Plane;

import java.util.List;

public interface PlaneDao extends CrudOperations<Plane> {

    String NAMED_QUERY_PLANES_FOR_PASSENGERS = "findPlanesForPassenger";
    String LAST_NAME_FIELD = "lastName";
    String FIRST_NAME_FIELD = "firstName";

    List<Plane> getPlanesForPassenger(Passenger passenger);
}
