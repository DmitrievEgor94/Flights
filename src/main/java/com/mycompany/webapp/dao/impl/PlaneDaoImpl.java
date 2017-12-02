package com.mycompany.webapp.dao.impl;


import com.mycompany.webapp.dao.core.AbstractDao;
import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Plane;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class PlaneDaoImpl extends AbstractDao<Plane> implements PlaneDao {

    private FlightDao flightDao = new FlightDaoImpl();

    public PlaneDaoImpl() {
        super(Plane.class);
    }

    @Override
    public void delete(Plane plane) {

        if (Optional.ofNullable(plane.getFlights()).isPresent()) {
            for (Flight flight : plane.getFlights()) {
                super.entityManager.refresh(flight);

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
    public List<Plane> getPlanesForPassenger(Passenger passenger) {
        Query query = super.entityManager.createNamedQuery(NAMED_QUERY_PLANES_FOR_PASSENGERS);

        query.setParameter(FIRST_NAME_FIELD, passenger.getFirstName());
        query.setParameter(LAST_NAME_FIELD, passenger.getLastName());

        return query.getResultList();
    }
}
