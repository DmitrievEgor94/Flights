package com.mycompany.webapp.dao.impl;


import com.mycompany.webapp.dao.core.AbstractDao;
import com.mycompany.webapp.dao.core.TicketDao;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Ticket;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {

    private static final String PASSENGER_ENTITY_FIELD = "passenger";
    private static final String FIRST_NAME_FIELD = "firstName";
    private static final String LAST_NAME_FIELD = "lastName";

    public TicketDaoImpl() {
        super(Ticket.class);
    }

    @Override
    public List<Ticket> getTicketsOfPassenger(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();

        CriteriaQuery<Ticket> query = criteriaBuilder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);

        Join<Ticket, Passenger> ticketPassengerJoin = root.join(PASSENGER_ENTITY_FIELD);

        query.where(criteriaBuilder.and(
                criteriaBuilder.equal(ticketPassengerJoin.get(FIRST_NAME_FIELD), firstName)
                , criteriaBuilder.equal(ticketPassengerJoin.get(LAST_NAME_FIELD), lastName)));

        return super.entityManager.createQuery(query).getResultList();
    }
}
