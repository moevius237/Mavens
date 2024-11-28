package org.example.Test;

import org.example.dao.GenericDao;
import org.example.dao.GenericDaoImpl;
import org.example.entity.Libro;

public class TestDao {
    public static void main(String[] args) {
        GenericDao<Libro> libroGenericDao = new GenericDaoImpl<>(Libro.class);
    }
}
