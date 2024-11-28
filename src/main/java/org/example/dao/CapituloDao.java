package org.example.dao;

import org.example.entity.Capitulo;
import org.example.entity.Libro;

import java.util.Optional;

public interface CapituloDao {
    void save (Capitulo capitulo);
    Optional<Capitulo> findById(Long id);
    Optional<Capitulo> update(Capitulo capitulo);
    void delete(Capitulo capitulo);
}
