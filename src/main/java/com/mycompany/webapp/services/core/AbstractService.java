package com.mycompany.webapp.services.core;

import com.mycompany.webapp.dao.core.CrudOperations;

import java.util.List;

public abstract class AbstractService<T> implements ServiceForCrudOperations<T> {

    private CrudOperations<T> crudOperator;

    @Override
    public String update(T ob) {
        String errorMessage = checkObject(ob);

        if (errorMessage == null) {
            crudOperator.update(ob);
        }

        return errorMessage;
    }

    @Override
    public String save(T ob) {
        String errorMessage = checkObject(ob);

        if (errorMessage == null) {
            crudOperator.save(ob);
        }

        return errorMessage;
    }

    @Override
    public List<T> readAll(long firstPositionId, long lastPositionId) {
        return crudOperator.findAll(firstPositionId, lastPositionId);
    }

    @Override
    public void delete(long id) {
        T ob = crudOperator.findById(id);

        if (ob != null) {
            crudOperator.delete(ob);
        }

    }

    @Override
    public T read(long id) {
        return crudOperator.findById(id);
    }

    @Override
    public long getNumberOfEntities() {
        return crudOperator.getNumberOfEntities();
    }

    protected void setCrudOperator(CrudOperations<T> crudOperator) {
        this.crudOperator = crudOperator;
    }

    abstract public String checkObject(T ob);
}
