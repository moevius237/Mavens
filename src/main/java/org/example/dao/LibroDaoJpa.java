package org.example.dao;

import jakarta.persistence.PersistenceException;
import org.example.entity.Libro;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class LibroDaoJpa implements LibroDao{

    @Override
    public void save(Libro libro) {
        try (Session session = HibernateUtil.getSession().openSession()){
            Transaction tr = null;
            try{
                tr = session.beginTransaction();
                session.persist(libro);
                tr.commit();
            }catch (PersistenceException e){
                if (tr != null) {
                    tr.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<Libro> findById(Long id) {
        try (Session session = HibernateUtil.getSession().openSession()){
            return Optional.of(session.get(Libro.class, id));
        }
    }

    @Override
    public Optional<Libro> update(Libro libro) {
        Libro libro1 = null;
        try (Session session = HibernateUtil.getSession().openSession()){
            Transaction tr = null;
            try{
                tr = session.beginTransaction();
                libro1 = session.merge(libro);
                tr.commit();
            }catch (PersistenceException e){
                if (tr != null) {
                    tr.rollback();
                }
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(libro1);
    }

    @Override
    public void delete(Libro libro) {
        try (Session session = HibernateUtil.getSession().openSession()){
            Transaction tr = null;
            try{
                tr = session.beginTransaction();
                session.remove(libro);
                tr.commit();

            }catch (PersistenceException e){
                if (tr != null){
                    tr.rollback();
                }
            }
        }
    }
}
