package com.mycompany.webapp.dao.impl;

import com.mycompany.webapp.context.BeanConfigDev;
import com.mycompany.webapp.dao.core.TicketDao;
import com.mycompany.webapp.models.Ticket;
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
public class TicketDaoImplTest {

    @Autowired
    private TicketDao ticketDao;

    @Test
    public void getTicketsOfPassengerTestTest() {
        List<Ticket> tickets = ticketDao.getTicketsOfPassenger("Egor", "Dmitriev");
        assertEquals(2, tickets.size());
    }

    @Test
    public void findAllTest() throws Exception {
        List all = ticketDao.findAll(1, 2);

        assertEquals(2, all.size());
    }
}
