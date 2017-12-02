package com.mycompany.webapp.services.core;

import java.util.List;

public interface ServiceForCrudOperations<T> {

    String save(T ob);

    List<T> readAll(long firstPositionId, long lastPositionId);

    String update(T ob);

    T read(long id);

    void delete(long id);

    long getNumberOfEntities();
}
