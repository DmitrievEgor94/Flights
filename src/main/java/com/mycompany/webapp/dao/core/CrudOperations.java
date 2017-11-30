package com.mycompany.webapp.dao.core;

import java.util.List;

public interface CrudOperations<T> {

    void save(T ob);

    List<T> findAll(long firstPositionId, long lastPositionId);

    void update(T ob);

    T findById(long id);

    void delete(T ob);

    long getNumberOfEntities();
}
