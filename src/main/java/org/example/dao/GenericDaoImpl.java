package org.example.dao;

import jakarta.persistence.PersistenceException;
import org.example.entity.Libro;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class GenericDaoImpl<T> implements GenericDao<T>{
    private final Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T t) {
        try (Session session = HibernateUtil.getSession().openSession()){
            Transaction tr = null;
            try {
                tr = session.beginTransaction();
                session.persist(t);
                tr.commit();
            }catch (PersistenceException e){

            }
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        try (Session session = HibernateUtil.getSession().openSession()){
            return Optional.of(session.get(entityClass, id));
        }
    }

    @Override
    public Optional<T> update(T t) {
        T t1 = null;
        try (Session session = HibernateUtil.getSession().openSession()){
            Transaction tr = null;
            try{
                tr = session.beginTransaction();
                t1 = session.merge(t);
                tr.commit();
            }catch (PersistenceException e){
                if (tr != null) {
                    tr.rollback();
                }
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(t1);
    }

    @Override
    public void delete(T t) {
        try (Session session = HibernateUtil.getSession().openSession()){
            Transaction tr = null;
            try{
                tr = session.beginTransaction();
                session.remove(t);
                tr.commit();

            }catch (PersistenceException e){
                if (tr != null){
                    tr.rollback();
                }
            }
        }
    }
}
