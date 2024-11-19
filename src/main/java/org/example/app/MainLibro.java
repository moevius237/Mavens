package org.example.app;

import org.example.entity.Capitulo;
import org.example.entity.Libro;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

public class MainLibro {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSession().openSession()){
            session.beginTransaction();
            Libro libro = new Libro();
            libro.setTitulo("Libro 1");
            libro.setTitulo("autor1");
            libro.setAnyoPublicacion(12);
            Capitulo p = new Capitulo();
            p.setTitulo("mono");
            p.setNumero("1");
            p.setLibroId(libro);
            session.persist(libro);
            session.persist(p);
            session.getTransaction().commit();
            Libro l = session.get(Libro.class,1);
            session.remove(l);
            session.getTransaction().commit();
        }
    }
}
