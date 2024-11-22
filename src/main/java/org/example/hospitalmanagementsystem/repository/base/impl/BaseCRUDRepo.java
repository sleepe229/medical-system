package org.example.hospitalmanagementsystem.repository.base.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.hospitalmanagementsystem.repository.base.CreateRepo;
import org.example.hospitalmanagementsystem.repository.base.ReadRepo;
import org.example.hospitalmanagementsystem.repository.base.UpdateRepo;

import java.util.List;

public class BaseCRUDRepo<T> implements CreateRepo<T>, ReadRepo<T>, UpdateRepo<T> {

    private final Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public BaseCRUDRepo(Class<T> entity) {
        this.entityClass = entity;
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T findById(Integer id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public List<T> findAll() {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(jpql, entityClass).getResultList();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
