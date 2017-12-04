package com.mycompany.webapp.services.core;

import com.mycompany.webapp.dao.core.CrudOperations;
import com.mycompany.webapp.services.ErrorMessages;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractService<T> implements ServiceForCrudOperations<T> {

    private CrudOperations<T> crudOperator;

    @Override
    @Transactional
    public String update(T ob) {
        if (ob == null) {
            return ErrorMessages.OBJECT_IS_NULL;
        }

        String errorMessage = checkObject(ob);

        if (errorMessage == null) {
            crudOperator.update(ob);
        }

        return errorMessage;
    }

    @Override
    @Transactional
    public String save(T ob) {
        if (ob == null) {
            return ErrorMessages.OBJECT_IS_NULL;
        }

        String errorMessage = checkObject(ob);

        if (errorMessage == null) {
            crudOperator.save(ob);
        }

        return errorMessage;
    }

    @Override
    @Transactional
    public List<T> readAll(long firstPositionId, long lastPositionId) {
        return crudOperator.findAll(firstPositionId, lastPositionId);
    }

    @Override
    @Transactional
    public void delete(long id) {
        T ob = crudOperator.findById(id);

        if (ob != null) {
            crudOperator.delete(ob);
        }
    }

    @Override
    @Transactional
    public T read(long id) {
        return crudOperator.findById(id);
    }

    @Override
    @Transactional
    public long getNumberOfEntities() {
        return crudOperator.getNumberOfEntities();
    }

    protected void setCrudOperator(CrudOperations<T> crudOperator) {
        this.crudOperator = crudOperator;
    }

    public abstract String checkObject(T ob);
}
