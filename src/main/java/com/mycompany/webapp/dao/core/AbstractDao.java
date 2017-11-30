package com.mycompany.webapp.dao.core;

import com.mycompany.webapp.source.Manager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDao<T> implements CrudOperations<T> {

    protected EntityManager entityManager;
    private Class entity;
    private String selectObjects;

    public AbstractDao(Class entity) {
        this.entity = entity;
        selectObjects = "SELECT ob" + " FROM " + entity.getSimpleName() + " ob " + "WHERE id BETWEEN  ";
        entityManager = Manager.getEntityManager();
    }

    @Override
    public void save(T ob) {
        entityManager.getTransaction().begin();
        entityManager.persist(ob);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<T> findAll(long firstPositionId, long lastPositionId) {
        String selectObjectsWithInterval = selectObjects + Long.toString(firstPositionId) + " AND " + Long.toString(lastPositionId);
        return entityManager.createQuery(selectObjectsWithInterval).getResultList();
    }

    @Override
    public void update(T ob) {
        entityManager.getTransaction().begin();
        entityManager.merge(ob);
        entityManager.getTransaction().commit();
    }

    @Override
    public T findById(long id) {
        Object ob = entityManager.find(entity, id);

        if (ob != null) {
            entityManager.refresh(ob);
        }

        return (T) ob;
    }

    @Override
    public void delete(T ob) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(ob) ? ob : entityManager.merge(ob));
        entityManager.getTransaction().commit();
    }

    @Override
    public long getNumberOfEntities() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery query = cb.createQuery(Long.class);

        Root objects = query.from(entity);

        query.select(cb.count(objects));

        return (long) entityManager.createQuery(query).getSingleResult();
    }

}
