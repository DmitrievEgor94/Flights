package com.mycompany.webapp.dao.impl;

import com.mycompany.webapp.context.BeanConfigDev;
import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.models.Flight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfigDev.class)
@ActiveProfiles("dev")
public class FlightDaoImplTest {

    @Autowired
    private FlightDao flightDao;

    @Test
    public void getFlightsOfPlaneTest() {
        List<Flight> flightsOfPlane = flightDao.getFlightsOfPlane("Aerobus 1");

        assertEquals(2, flightsOfPlane.size());
    }

    @Test
    public void findAllTest() throws Exception {
        List all = flightDao.findAll(1, 2);

        assertEquals(2, all.size());
    }
}