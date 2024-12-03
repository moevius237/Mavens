package org.example.Test;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.hibernate.Session;

public class CriteriaTest3 {
    public static void main(String[] args) {
        metodo12();
    }
    public static void metodo10(){
        try (Session session = HibernateUtil.getSession().openSession()){
            CriteriaBuilder criteriaBuilder4 = session.getCriteriaBuilder();
            CriteriaQuery<ResenyaLibroDto> criteriaQuery4 = criteriaBuilder4.createQuery(ResenyaLibroDto.class);
            Root<Resenya> resenyaRoot = criteriaQuery4.from(Resenya.class);
            Join<Resenya, Libro> libroResenyaJoin = resenyaRoot.join("libro");
            criteriaQuery4.multiselect(libroResenyaJoin.get("titulo"), criteriaBuilder4.avg(resenyaRoot.get("puntuacion")))
                    .groupBy(libroResenyaJoin.get("id"))
                    .having(criteriaBuilder4.gt(criteriaBuilder4.count(libroResenyaJoin.get("id")),5))
                    .having(criteriaBuilder4.gt(criteriaBuilder4.avg(resenyaRoot.get("puntuacion")) ,4.5));

            System.out.println(session.createQuery(criteriaQuery4).getResultList());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void metodo11(){
        try (Session session = HibernateUtil.getSession().openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
            Root<LibroAutor> libroAutorRoot = criteriaQuery.from(LibroAutor.class);
            Join<LibroAutor, Autor> autorJoin = libroAutorRoot.join("autor");
            Join<LibroAutor, Libro> libroJoin = libroAutorRoot.join("libro");
            Join<Libro, LibroAutor> libroAutorJoin = libroAutorRoot.join("autores");
            criteriaQuery.multiselect(autorJoin.get("nombre"), builder.countDistinct(libroAutorRoot.get("libro")))
                    .where(builder.notEqual(libroAutorRoot.get("autor"),libroAutorJoin.get("autor")))
                    .groupBy(autorJoin.get("id"))
                    .having(builder.gt( builder.countDistinct(libroAutorRoot.get("libro")),1L));
        }catch(PersistenceException e){
            e.printStackTrace();
        }
    }
    public static void metodo12(){
        try (Session session = HibernateUtil.getSession().openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CapituloLibroDto> capituloCriteriaQuery = criteriaBuilder.createQuery(CapituloLibroDto.class);
            Root<Capitulo> capituloRoot = capituloCriteriaQuery.from(Capitulo.class);
            Join<Capitulo, Libro> capituloLibroJoin = capituloRoot.join("libroId");
            capituloCriteriaQuery.multiselect(capituloLibroJoin.get("titulo"),criteriaBuilder.sum(capituloRoot.get("numeroPaginas")))
                    .groupBy(capituloLibroJoin.get("id"))
                    .where(criteriaBuilder.gt(capituloRoot.get("numeroPaginas"),20));
            System.out.println(session.createQuery(capituloCriteriaQuery).getResultList());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
