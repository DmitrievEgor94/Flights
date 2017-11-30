package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.TicketDao;
import com.mycompany.webapp.dao.impl.TicketDaoImpl;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServiceTicket;

import java.util.List;

public class ServiceTicketImpl extends AbstractService<Ticket> implements ServiceTicket {

    private final int ABSENT_AMOUNT = 0;

    private TicketDao ticketDao = new TicketDaoImpl();

    public ServiceTicketImpl() {
        super.setCrudOperator(ticketDao);
    }

    @Override
    public boolean save(Ticket ob) {
        if ((ob.getCost() == ABSENT_AMOUNT) || (ob.getDate() == null) || (ob.getFlight() == null)
                || (ob.getPlane() == null) || (ob.getCost() == ABSENT_AMOUNT)) {
            return false;
        } else {
            ticketDao.save(ob);
            return true;
        }
    }

    @Override
    public boolean checkObject(Ticket ob) {
        return (ob.getCost() != ABSENT_AMOUNT) && (ob.getDate() != null) && (ob.getFlight() != null)
                && (ob.getPlane() != null) && (ob.getCost() != ABSENT_AMOUNT);
    }

    @Override
    public List<Ticket> getTicketsOfPassenger(Passenger passenger) {
        return ticketDao.getTicketsOfPassenger(passenger);
    }
}
