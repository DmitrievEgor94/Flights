package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.FlightDao;
import com.mycompany.webapp.dao.impl.FlightDaoImpl;
import com.mycompany.webapp.models.Flight;
import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServiceFlight;

import java.util.List;

public class ServiceFlightImpl extends AbstractService<Flight> implements ServiceFlight {

    private FlightDao flightDao = new FlightDaoImpl();

    public ServiceFlightImpl() {
        super.setCrudOperator(flightDao);
    }

    @Override
    public boolean checkObject(Flight ob) {
        return ob.getFlightNumber() != null;
    }

    @Override
    public List<Flight> getFlightsOfPlane(Plane plane) {
        return flightDao.getFlightsOfPlane(plane);
    }
}
