package com.mycompany.webapp.services.impl;

import com.mycompany.webapp.dao.core.PlaneDao;
import com.mycompany.webapp.models.Plane;
import com.mycompany.webapp.services.ErrorMessages;
import com.mycompany.webapp.services.core.AbstractService;
import com.mycompany.webapp.services.core.ServicePlane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicePlaneImpl extends AbstractService<Plane> implements ServicePlane {

    private PlaneDao planeDao;

    @Autowired
    public ServicePlaneImpl(PlaneDao planeDao) {
        this.planeDao = planeDao;
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
    public List<Plane> getPlanesByPassenger(String firstName, String lastName) {
        return planeDao.getPlanesForPassenger(firstName, lastName);
    }
}
