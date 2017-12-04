package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.context.BeanConfigDev;
import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.models.Ticket;
import com.mycompany.webapp.services.core.ServiceFlight;
import com.mycompany.webapp.services.core.ServicePassenger;
import com.mycompany.webapp.services.core.ServicePlane;
import com.mycompany.webapp.services.core.ServiceTicket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfigDev.class)
@ActiveProfiles("dev")
public class ServiceTicketImplTest {

    @Autowired
    private ServiceTicket serviceTicket;

    @Autowired
    private ServiceFlight serviceFlight;

    @Autowired
    private ServicePassenger servicePassenger;

    @Autowired
    private ServicePlane servicePlane;


    @Test
    public void checkObject() {
        Ticket ticket = new Ticket(new java.util.Date(), 1, 100);

        assertNotNull(serviceTicket.save(ticket));

        Plane plane = new Plane("1212");
        servicePlane.save(plane);

        Flight flight = new Flight("x12");
        flight.getPlanes().add(plane);

        serviceFlight.save(flight);

        Passenger passenger = new Passenger("Andrey", "Gladchencko");
        servicePassenger.save(passenger);

        assertNull(serviceTicket.save(ticket));
    }
}