package org.example.Test;

import jakarta.persistence.PersistenceException;
import org.example.entity.Autor;
import org.example.entity.Libro;
import org.example.entity.LibroAutor;
import org.example.entity.LibroAutorId;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction tr = null;
            try{
                tr = session.beginTransaction();

                Libro l = session.get(Libro.class, 1);
                Autor autor = session.get(Autor.class ,1);

                System.out.println(l);
                tr.commit();

            }catch (PersistenceException e){
                if  (tr != null){
                    tr.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
