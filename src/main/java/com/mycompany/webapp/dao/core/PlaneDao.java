package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.models.Plane;

import java.util.List;

public interface PlaneDao extends CrudOperations<Plane> {

    List<Plane> getPlanesForPassenger(String firstName, String lastName);
}
