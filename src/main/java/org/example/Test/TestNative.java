package org.example.Test;

import org.example.entity.Autor;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

public class TestNative {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSession().openSession()){
            try {
                NativeQuery<Autor> nativeQuery =
                        session.createNativeQuery("SELECT * FROM autor a WHERE a.nombre LIKE ?1", Autor.class);
                nativeQuery.setParameter(1,"A%");
                System.out.println(nativeQuery.getResultList());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
