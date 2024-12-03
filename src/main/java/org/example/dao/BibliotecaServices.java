package org.example.dao;

import org.example.entity.Capitulo;
import org.example.entity.Libro;

import java.util.Optional;

public class BibliotecaServices {
    private final LibroDao libroDao;
    private final CapituloDao capituloDao;

    public BibliotecaServices() {
        this.libroDao = new LibroDaoJpa();
        this.capituloDao = new CapituloDaoImpl();
    }

}
