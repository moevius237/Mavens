package org.example.dao;

import org.example.entity.Capitulo;

import java.util.Optional;

public interface GenericDao<T> {
    void save (T t);
    Optional<T> findById(Long id);
    Optional<T> update(T t);
    void delete(T t);
}
