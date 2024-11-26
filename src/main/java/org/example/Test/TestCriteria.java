package org.example.Test;

import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import org.example.entity.Autor;
import org.example.entity.Capitulo;
import org.example.entity.Libro;
import org.example.entity.LibroAutor;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

public class TestCriteria {
    public static void main(String[] args) {
        /*
        CriteriaBuilder --> fabrica para crear componentes de query
        CriteriaQuery -->donde se construye la query, se construye utilizando bloques
        Predicate Order, Expression y Selection

         */
        try (Session session = HibernateUtil.getSession().openSession()){
            try {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Autor> criteriaQuery = builder.createQuery(Autor.class);
                Root<Autor> autorRoot = criteriaQuery.from(Autor.class);
                criteriaQuery
                        .select(autorRoot)
                        .where(builder.like(autorRoot.get("nombre"),"P%"))
                        .orderBy(builder.asc(autorRoot.get("nombre")));
                System.out.println(session.createQuery(criteriaQuery).getResultList());

                CriteriaBuilder builder1 = session.getCriteriaBuilder();
                CriteriaQuery<Tuple> criteriaQuery1 = builder1.createTupleQuery();
                Root<Libro> libroRoot = criteriaQuery1.from(Libro.class);
                Join<Libro, Capitulo> capituloJoin = libroRoot.join("capitulos", JoinType.INNER);
                criteriaQuery1
                        .multiselect(libroRoot.get("nombre"),capituloJoin.get("titulo"))
                        .where(builder1.like(capituloJoin.get("titulo"), "%introduccion"));
                System.out.println(session.createQuery(criteriaQuery1).getResultList());

                //Todo nombres y rol de los autores que han escrito el libro java Guide
                CriteriaBuilder builder2 = session.getCriteriaBuilder();
                CriteriaQuery<Autor> autorCriteriaQuery = builder2.createQuery(Autor.class);
                Root<Autor> autorRoot1 = autorCriteriaQuery.from(Autor.class);
                Join<Autor, LibroAutor> libroJoin = autorRoot1.join("libros",JoinType.INNER);
                Join<LibroAutor, Libro> libroAutorLibroJoin = libroJoin.join("libro",JoinType.INNER);
                autorCriteriaQuery
                        .multiselect(autorRoot1.get("nombre"),libroJoin.get("rol"))
                        .where(builder2.like(libroAutorLibroJoin.get("titulo"),"Java Guide"));
                System.out.println(session.createQuery(autorCriteriaQuery).getResultList());

                //Todo mostrar el numero de autores por libro y tambien muestro el nombre de libro
                CriteriaBuilder builder3 = session.getCriteriaBuilder();
                CriteriaQuery<Tuple> tupleCriteriaQuery = builder3.createTupleQuery();
                Root<Libro> root = tupleCriteriaQuery.from(Libro.class);
                Join<Libro, LibroAutor> libroAutorJoin = root.join("autores");
                tupleCriteriaQuery.multiselect(root.get("titulo"),builder3.count(libroAutorJoin))
                        .groupBy(root.get("id"));
                System.out.println(session.createQuery(tupleCriteriaQuery).getResultList());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
