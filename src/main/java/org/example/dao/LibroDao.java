package org.example.dao;

import org.example.entity.Libro;

import java.util.Optional;

public interface LibroDao {
    void save (Libro libro);
    Optional<Libro> findById(Long id);
    Optional<Libro> update(Libro libro);
    void delete(Libro libro);
}
