package com.mycompany.webapp.services.core;

import com.mycompany.webapp.dao.core.CrudOperations;

import java.util.List;

public abstract class AbstractService<T> implements ServiceForCrudOperations<T> {

    private CrudOperations<T> crudOperator;

    @Override
    public boolean update(T ob) {
        if (checkObject(ob)) {
            crudOperator.update(ob);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean save(T ob) {
        if (checkObject(ob)) {
            crudOperator.save(ob);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<T> readAll(long firstPositionId, long lastPositionId) {
        return crudOperator.findAll(firstPositionId, lastPositionId);
    }

    @Override
    public void delete(long id) {
        T ob = crudOperator.findById(id);
        crudOperator.delete(ob);
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

    abstract public boolean checkObject(T ob);
}
