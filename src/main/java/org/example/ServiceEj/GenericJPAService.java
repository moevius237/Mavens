package org.example.ServiceEj;

import org.example.DaoEj.DaoGenerico;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class GenericJPAService<T> implements DaoGenerico<T> {

    @Override
    public void save(T t) {
        try (Session session = HibernateUtil.getSession().openSession()){
            Transaction tr = null;
            try{
                tr = session.beginTransaction();
                session.persist(t);
                tr.commit();
            }catch (HibernateException e){
                e.getStackTrace();
                tr.rollback();
            }
        }
    }

    @Override
    public void delete(T t) {
        try (Session session = HibernateUtil.getSession().openSession()){
            Transaction tr = null;
            try{
                tr = session.beginTransaction();
                session.remove(t);
                tr.commit();
            }catch (HibernateException e){
                e.getStackTrace();
                tr.rollback();
            }
        }
    }
}
