package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.dao.core.PassengerDao;
import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.dao.core.TicketDao;
import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.ErrorMessages;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceTicketImpl extends AbstractService<Ticket> implements ServiceTicket {

    private TicketDao ticketDao;
    private PlaneDao planeDao;
    private FlightDao flightDao;
    private PassengerDao passengerDao;

    @Autowired
    public ServiceTicketImpl(TicketDao ticketDao, PlaneDao planeDao, FlightDao flightDao, PassengerDao passengerDao) {
        this.ticketDao = ticketDao;
        this.planeDao = planeDao;
        this.flightDao = flightDao;
        this.passengerDao = passengerDao;
        super.setCrudOperator(ticketDao);
    }

    @Override
    public String checkObject(Ticket ticket) {
        if ((ticket.getCost() == 0) || (ticket.getDate() == null) || (ticket.getSeat() == 0)
                || (ticket.getPassenger() == null) || (ticket.getFlight() == null)
                || (ticket.getPlane() == null)) {
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
    public List<Ticket> getTicketsByPassenger(String firstName, String lastName) {
        return ticketDao.getTicketsOfPassenger(firstName, lastName );
    }
}
