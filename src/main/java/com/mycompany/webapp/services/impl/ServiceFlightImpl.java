package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.ErrorMessages;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServiceFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceFlightImpl extends AbstractService<Flight> implements ServiceFlight {

    private FlightDao flightDao;

    private PlaneDao planeDao;

    @Autowired
    public ServiceFlightImpl(FlightDao flightDao, PlaneDao planeDao) {
        this.flightDao = flightDao;
        this.planeDao = planeDao;
        super.setCrudOperator(flightDao);
    }

    @Override
    public String checkObject(Flight ob) {
        if (ob.getFlightNumber() == null) {
            return ErrorMessages.FILL_FIELDS_MESSAGE;
        }

        if (ob.getPlanes() != null && !ob.getPlanes().isEmpty()) {
            for (Plane plane : ob.getPlanes()) {
                if (planeDao.findById(plane.getId()) == null) {
                    return ErrorMessages.PLANE_DOES_NOT_EXIST + " id:" + plane.getId();
                }
            }
        } else {
            return ErrorMessages.NO_PLANES_FOR_FLIGHT;
        }

        return null;
    }

    @Override
    public List<Flight> getFlightsByPlane(String planeNumber) {
        return flightDao.getFlightsOfPlane(planeNumber);
    }
}
