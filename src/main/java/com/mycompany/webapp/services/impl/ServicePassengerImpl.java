package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.PassengerDao;
import com.mycompany.webapp.dao.impl.PassengerDaoImpl;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServicePassenger;

import java.util.List;

public class ServicePassengerImpl extends AbstractService<Passenger> implements ServicePassenger {

    private PassengerDao passengerDao = new PassengerDaoImpl();

    public ServicePassengerImpl() {
        super.setCrudOperator(passengerDao);
    }

    @Override
    public boolean checkObject(Passenger ob) {
        return ob.getLastName() != null && ob.getFirstName() != null;
    }

    @Override
    public List<Passenger> getPassengersWithSeveralTickets() {
        return passengerDao.getPassengersWithSeveralTickets();
    }
}
