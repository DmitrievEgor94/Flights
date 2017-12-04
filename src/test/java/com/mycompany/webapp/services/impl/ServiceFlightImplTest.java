package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.context.BeanConfigDev;
import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.ServiceFlight;
import com.mycompany.webapp.services.core.ServicePlane;
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
public class ServiceFlightImplTest {

    @Autowired
    private ServiceFlight serviceFlight;

    @Autowired
    private ServicePlane servicePlane;

    @Test
    public void checkObjectTest() {
        Flight flight = new Flight("x12");

        Plane plane = new Plane("1212");

        assertNotNull(serviceFlight.save(flight));

        servicePlane.save(plane);
        flight.getPlanes().add(plane);

        assertNull(serviceFlight.save(flight));
    }

}