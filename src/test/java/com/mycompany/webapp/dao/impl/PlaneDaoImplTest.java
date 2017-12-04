package com.mycompany.webapp.dao.impl;

import com.mycompany.webapp.context.BeanConfigDev;
import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.models.Plane;
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
public class PlaneDaoImplTest {

    @Autowired
    private PlaneDao planeDao;

    @Test
    public void getPlanesForPassengerTest() {
        List<Plane> planes = planeDao.getPlanesForPassenger("Egor", "Dmitriev");
        assertEquals(2, planes.size());
    }

    @Test
    public void findAllTest() throws Exception {
        List all = planeDao.findAll(1, 2);

        assertEquals(2, all.size());
    }

    @Test
    public void deleteTestTest() {
        Plane plane = planeDao.findById(1);

        planeDao.delete(plane);

        assertEquals(null, planeDao.findById(1));
    }
}
