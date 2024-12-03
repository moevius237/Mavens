package org.example.DaoEj;


import java.util.Optional;

public interface DaoGenerico <T>{
    void save (T t);
    void delete(T t);

}
