package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.dao.impl.FlightDaoImpl;
import com.mycompany.webapp.dao.impl.PlaneDaoImpl;
import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServiceFlight;

import java.util.List;
import java.util.Optional;

public class ServiceFlightImpl extends AbstractService<Flight> implements ServiceFlight {

    private FlightDao flightDao = new FlightDaoImpl();
    private PlaneDao planeDao = new PlaneDaoImpl();

    public ServiceFlightImpl() {
        super.setCrudOperator(flightDao);
    }

    @Override
    public String checkObject(Flight ob) {
        if (ob.getFlightNumber() == null) {
            return ErrorMessages.FILL_FIELDS_MESSAGE;
        }

        if (Optional.ofNullable(ob.getPlanes()).isPresent()
                && !ob.getPlanes().isEmpty()) {
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
    public List<Flight> getFlightsOfPlane(Plane plane) {
        return flightDao.getFlightsOfPlane(plane);
    }
}
