package com.mycompany.webapp.dao.impl;


import com.mycompany.webapp.dao.core.AbstractDao;
import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;

import javax.persistence.Query;
import java.util.List;

public class FlightDaoImpl extends AbstractDao<Flight> implements FlightDao {

    private static final String CLASS_NAME = "Flight";

    private static final String JQPL_FLIGHTS_FOR_PLANE = "SELECT f FROM " + CLASS_NAME + " f join f.planes p " +
            " WHERE p.planeNumber = :planeNumber";

    public FlightDaoImpl() {
        super(Flight.class);
    }

    @Override
    public List<Flight> getFlightsOfPlane(Plane plane) {
        Query query = super.entityManager.createQuery(JQPL_FLIGHTS_FOR_PLANE);

        query.setParameter(1, plane.getPlaneNumber());

        return query.getResultList();
    }
}
