package com.mycompany.webapp.dao.impl;

import com.mycompany.webapp.context.BeanConfigDev;
import com.mycompany.webapp.dao.core.PassengerDao;
import com.mycompany.webapp.models.Passenger;
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
public class PassengerDaoImplTest {

    @Autowired
    private PassengerDao passengerDao;

    @Test
    public void getPassengersWithSeveralTicketsTest() {
        List<Passenger> passengersWithSeveralTickets = passengerDao.getPassengersWithSeveralTickets();

        assertEquals(1, passengersWithSeveralTickets.size());
        assertEquals("Egor", passengersWithSeveralTickets.get(0).getFirstName());
    }

    @Test
    public void findAllTest() throws Exception {
        List all = passengerDao.findAll(1, 2);

        assertEquals(2, all.size());
    }
}
