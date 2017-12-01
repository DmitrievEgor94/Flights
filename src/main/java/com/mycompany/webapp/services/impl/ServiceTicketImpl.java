package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.dao.core.PassengerDao;
import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.dao.core.TicketDao;
import com.mycompany.webapp.dao.impl.FlightDaoImpl;
import com.mycompany.webapp.dao.impl.PassengerDaoImpl;
import com.mycompany.webapp.dao.impl.PlaneDaoImpl;
import com.mycompany.webapp.dao.impl.TicketDaoImpl;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServiceTicket;

import java.util.List;

public class ServiceTicketImpl extends AbstractService<Ticket> implements ServiceTicket {

    private TicketDao ticketDao = new TicketDaoImpl();
    private PlaneDao planeDao = new PlaneDaoImpl();
    private FlightDao flightDao = new FlightDaoImpl();
    private PassengerDao passengerDao = new PassengerDaoImpl();

    public ServiceTicketImpl() {
        super.setCrudOperator(ticketDao);
    }

    @Override
    public String checkObject(Ticket ticket) {
        if ((ticket.getCost() == 0) || (ticket.getDate() == null) || (ticket.getFlight() == null)
                && (ticket.getPlane() == null) || (ticket.getCost() == 0)) {
            return ErrorMessages.FILL_FIELDS_MESSAGE;
        }

        if (passengerDao.findById(ticket.getPassenger().getId()) == null) {
            return ErrorMessages.PASSENGER_DOES_NOT_EXIST;
        }

        if (planeDao.findById(ticket.getPlane().getId()) == null) {
            return ErrorMessages.PLANE_DOES_NOT_EXIST;
        }

        if (flightDao.findById(ticket.getFlight().getId()) == null) {
            return ErrorMessages.FLIGHT_DOES_NOT_EXIST;
        }

        return null;
    }

    @Override
    public List<Ticket> getTicketsOfPassenger(Passenger passenger) {
        return ticketDao.getTicketsOfPassenger(passenger);
    }
}
