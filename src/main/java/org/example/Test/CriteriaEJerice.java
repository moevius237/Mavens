package org.example.Test;

import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CriteriaEJerice {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSession().openSession()){
            try {
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<String > criteriaQuery = criteriaBuilder.createQuery(String.class);
                Root<Libro> libroRoot = criteriaQuery.from(Libro.class);
                criteriaQuery.select(libroRoot.get("titulo"))
                        .where(criteriaBuilder.greaterThan(libroRoot.get("anyoPublicacion"),2020));
                System.out.println(session.createQuery(criteriaQuery).getResultList());

                CriteriaBuilder criteriaBuilder1 = session.getCriteriaBuilder();
                CriteriaQuery<Tuple> criteriaQuery1 = criteriaBuilder1.createTupleQuery();
                Root<Libro> libroRoot1 = criteriaQuery1.from(Libro.class);
                Join<Libro, DetalleLibro> detalleLibroJoin = libroRoot1.join("detalleLibro");
                criteriaQuery.multiselect(criteriaBuilder1.construct(DetalleLibroDto.class,libroRoot1.get("titulo"),detalleLibroJoin.get("resumen")))
                        .orderBy(criteriaBuilder1.asc(libroRoot1.get("anyoPublicacion")));
                List<Tuple> lista =  session.createQuery(criteriaQuery1).getResultList();
                for (int i = 0; i < 3; i++) {
                    System.out.println(lista.get(i));
                }

                CriteriaBuilder criteriaBuilder2 = session.getCriteriaBuilder();
                CriteriaQuery<Tuple> criteriaQuery2 = criteriaBuilder2.createTupleQuery();
                Root<Capitulo> capituloRoot = criteriaQuery2.from(Capitulo.class);
                Join<Capitulo,Libro> capituloLibroJoin = capituloRoot.join("libroId");
                criteriaQuery2.select(capituloLibroJoin.get("titulo"))
                        .where(criteriaBuilder2.greaterThanOrEqualTo(capituloRoot.get("numeroPaginas"),10));
                System.out.println(session.createQuery(criteriaQuery2).getResultList());

                CriteriaBuilder criteriaBuilder3 = session.getCriteriaBuilder();
                CriteriaQuery<Tuple> criteriaQuery3 = criteriaBuilder3.createTupleQuery();
                Root<LibroAutorId> libroRoot2 = criteriaQuery3.from(LibroAutorId.class);
                Join<LibroAutorId, Libro> libroLibroAutorIdJoin = libroRoot2.join("libroId");
                Join<Autor, LibroAutorId> autorIdAutorJoin = libroRoot2.join("autorId");
                criteriaQuery3.select(libroLibroAutorIdJoin.get("titulo"))
                        .where(criteriaBuilder3.like(autorIdAutorJoin.get("nombre"),"A%"));
                System.out.println(session.createQuery(criteriaQuery3).getResultList());


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
