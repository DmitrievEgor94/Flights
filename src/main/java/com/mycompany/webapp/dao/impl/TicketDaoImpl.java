package com.mycompany.webapp.dao.impl;


import com.mycompany.webapp.dao.core.AbstractDao;
import com.mycompany.webapp.dao.core.TicketDao;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Ticket;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {

    public TicketDaoImpl() {
        super(Ticket.class);
    }

    @Override
    public List<Ticket> getTicketsOfPassenger(Passenger passenger) {
        CriteriaBuilder criteriaBuilder = super.entityManager.getCriteriaBuilder();

        CriteriaQuery<Ticket> query = criteriaBuilder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);

        Join<Ticket, Passenger> ticketPassengerJoin = root.join(PASSENGER_ENTITY_FIELD);

        query.where(criteriaBuilder.equal(ticketPassengerJoin.get(ID_FIELD_NAME), passenger.getId()));

        query.orderBy(criteriaBuilder.asc(root.get(DATE_FIELD_NAME)));

        return super.entityManager.createQuery(query).getResultList();
    }

}
