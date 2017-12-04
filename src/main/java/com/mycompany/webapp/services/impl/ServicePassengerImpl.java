package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.PassengerDao;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.services.ErrorMessages;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServicePassenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicePassengerImpl extends AbstractService<Passenger> implements ServicePassenger {

    private PassengerDao passengerDao;

    @Autowired
    public ServicePassengerImpl(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
        super.setCrudOperator(passengerDao);
    }

    @Override
    public String checkObject(Passenger ob) {
        if ((ob.getLastName() == null) || (ob.getFirstName() == null)) {
            return ErrorMessages.FILL_FIELDS_MESSAGE;
        }
        return null;
    }

    @Override
    public List<Passenger> getPassengersWithSeveralTickets() {
        return passengerDao.getPassengersWithSeveralTickets();
    }
}
