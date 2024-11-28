package org.example.dao;

import org.example.entity.Capitulo;

public class CapituloDaoImpl extends GenericDaoImpl<Capitulo> implements CapituloDao{
    public CapituloDaoImpl() {
        super(Capitulo.class);
    }
}
