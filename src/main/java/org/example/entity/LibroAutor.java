package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "libro_has_autor")
public class LibroAutor {

    @EmbeddedId
    private LibroAutorId id;

    @ManyToOne
    @MapsId("libroId")
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne
    @MapsId("autorId")
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Column(nullable = false)
    private String rol;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setId(LibroAutorId id) {
        this.id = id;
    }

    public LibroAutorId getId() {
        return id;
    }

    @Override
    public String toString() {
        return "LibroAutor{" +
                "id=" + id +
                ", libro=" + libro +
                ", autor=" + autor +
                ", rol='" + rol + '\'' +
                '}';
    }
}
