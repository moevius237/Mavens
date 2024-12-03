package org.example.dao;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.Root;
import org.example.entity.Capitulo;
import org.example.entity.Libro;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class LibroDaoJpa implements LibroDao{

    @Override
    public List<Capitulo> findByLibroId(Long id) {
        String hql = "SELECT * FROM Capitulo c WHERE c.libro.id = ?1";
        try (Session session = HibernateUtil.getSession().openSession()){
            Query<Capitulo> query = session.createQuery(hql, Capitulo.class);
            query.setParameter(1,id);
            return query.getResultList();
        }catch (PersistenceException e){
            e.printStackTrace();
            return null;
        }
    }
}
