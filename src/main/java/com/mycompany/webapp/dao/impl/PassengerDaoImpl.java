package com.mycompany.webapp.dao.impl;


import com.mycompany.webapp.dao.core.AbstractDao;
import com.mycompany.webapp.dao.core.PassengerDao;
import com.mycompany.webapp.models.Passenger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PassengerDaoImpl extends AbstractDao<Passenger> implements PassengerDao {

    private static final String NAMED_QUERY_FIND_PASSENGER_WITH_SEVERAL_TICKETS = "findPassengersWithSeveralTickets";

    public PassengerDaoImpl() {
        super(Passenger.class);
    }

    @Override
    public List<Passenger> getPassengersWithSeveralTickets() {
        Query query = super.entityManager.createNamedQuery(NAMED_QUERY_FIND_PASSENGER_WITH_SEVERAL_TICKETS);

        return query.getResultList();
    }
}
