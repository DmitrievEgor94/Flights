package com.mycompany.webapp.services.core;

import com.mycompany.webapp.models.Plane;

import java.util.List;

public interface ServicePlane extends ServiceForCrudOperations<Plane> {

    List<Plane> getPlanesByPassenger(String firstName, String lastName);
}
