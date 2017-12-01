package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.dao.impl.PlaneDaoImpl;
import com.mycompany.webapp.models.Passenger;
import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServicePlane;

import java.util.List;

public class ServicePlaneImpl extends AbstractService<Plane> implements ServicePlane {

    private PlaneDao planeDao = new PlaneDaoImpl();

    public ServicePlaneImpl() {
        super.setCrudOperator(planeDao);
    }

    @Override
    public String checkObject(Plane ob) {
        if (ob.getPlaneNumber() == null) {
            return ErrorMessages.FILL_FIELDS_MESSAGE;
        }

        return null;
    }

    @Override
    public List<Plane> getPlanesForPassenger(Passenger passenger) {
        return null;
    }

}
