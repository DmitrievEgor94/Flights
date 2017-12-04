package com.mycompany.webapp.dao.impl;


import com.mycompany.webapp.dao.core.AbstractDao;
import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PlaneDaoImpl extends AbstractDao<Plane> implements PlaneDao {

    private static final String NAMED_QUERY_PLANES_FOR_PASSENGERS = "findPlanesForPassenger";
    private static final String LAST_NAME_FIELD = "lastName";
    private static final String FIRST_NAME_FIELD = "firstName";

    @Autowired
    private FlightDao flightDao;

    public PlaneDaoImpl() {
        super(Plane.class);
    }

    @Override
    public void delete(Plane plane) {

        if (plane.getFlights() != null) {
            for (Flight flight : plane.getFlights()) {

                flight.getPlanes().remove(plane);

                if (flight.getPlanes().isEmpty()) {
                    flightDao.delete(flight);
                } else {
                    flightDao.update(flight);
                }
            }
        }

        super.delete(plane);
    }

    @Override
    public List<Plane> getPlanesForPassenger(String firstName, String lastName) {
        Query query = super.entityManager.createNamedQuery(NAMED_QUERY_PLANES_FOR_PASSENGERS);

        query.setParameter(FIRST_NAME_FIELD, firstName);
        query.setParameter(LAST_NAME_FIELD, lastName);

        return query.getResultList();
    }
}
