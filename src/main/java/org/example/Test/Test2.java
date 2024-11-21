package org.example.Test;

import jakarta.persistence.Tuple;
import org.example.entity.Libro;
import org.example.entity.TituloAnyoDTO;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSession().openSession()) {
//            String jfb = "Select l FROM Libro l WHERE l.titulo LIKE :tituloParcial";//Libro se refiere a la clase no a la tabla
//            String jfb1 = "Select l.titulo, l.anyoPublicacion FROM Libro l WHERE l.titulo LIKE ?1";
//            String jfb2 = "Select l FROM Libro l WHERE l.titulo LIKE ?1";
//            Query<Libro> query= session.createQuery(jfb1, Libro.class);
//            query.setParameter(1,"%java%");
//            query.setParameter("tituloParcial","%java%");
//            List<Libro> listas = query.getResultList();
////            System.out.println(listas);
//
//            String hs = "SELECT l.titulo as titulo, l.anyoPublicacion FROM Libro l WHERE l.titulo LIKE ?1";
//            Query<Tuple> query = session.createQuery(hs,Tuple.class);
//            query.setParameter(1,"%java%");
//            Stream<Tuple> stream = query.getResultStream();
//            stream.map(
//                    tuple -> new TituloAnyoDTO(tuple.get("titulo", String.class),tuple.get(1,Integer.class))).forEach(System.out::println);;
//        }
//            String sql = "SELECT l FROM Libro l JOIN capitulos capi WHERE capi.titulo LIKE ?1";
//            Query<Libro> query = session.createQuery(sql,Libro.class);
//            query.setParameter(1,"libro");
//            System.out.println(query.getFirstResult());
            //quiero el nombre del autor cuyo detalle del libro es el id 1
            //Imprime el nombre de los autores y la cantidad libros en los que ha participado
            String sql = "SELECT a.nombre, count(l) From Autor a JOIN Libro l JOIN DetalleLibro d WHERE d.id = ?1 GROUP BY (l)";
            Query<String> query = session.createQuery(sql,String.class);
            query.setParameter(1,1);
            System.out.println(query.getFirstResult());
        }
    }
}
