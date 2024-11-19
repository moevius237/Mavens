package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

@Embeddable
public class LibroAutorId {

    public LibroAutorId(){}

    @Column(name = "libro_id")
    private Long libroId;

    @Column(name = "autor_id")
    private Long autorId;

    public LibroAutorId(Long libroId, Long autorId) {
        this.libroId = libroId;
        this.autorId = autorId;
    }



    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    @Override
    public String toString() {
        return "LibroAutorId{" +
                "libroId=" + libroId +
                ", autorId=" + autorId +
                '}';
    }
}
