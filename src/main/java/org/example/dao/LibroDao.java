package org.example.dao;

import org.example.entity.Capitulo;
import org.example.entity.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroDao {
    List<Capitulo> findByLibroId(Long id);
}
