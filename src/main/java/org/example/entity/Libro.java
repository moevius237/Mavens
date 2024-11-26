package org.example.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "libro")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToMany(mappedBy = "libro")
    private Set<LibroAutor> autores = new HashSet<>();

    @Column(name = "titulo",nullable = false)
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    @Column(name = "anyo_publicacion")
    private int anyoPublicacion;

    @OneToOne(mappedBy = "libro")
    private DetalleLibro detalleLibro;

    @ManyToMany
    @JoinTable(name = "libro_has_tema",
    joinColumns = @JoinColumn(name = "libro_id"),
    inverseJoinColumns = @JoinColumn(name = "tema_id"))
    private Set<Tema> temas = new HashSet<>();

    @OneToMany(mappedBy = "libro",orphanRemoval = true)//Es el nombre del atributo de la otra clase
    private Set<Resenya> resenyas = new HashSet<>();

    public Integer getAnyoPublicacion() {
        return anyoPublicacion;
    }

    public void setAnyoPublicacion(Integer anyoPublicacion) {
        this.anyoPublicacion = anyoPublicacion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", id=" + id +
                ", anyoPublicacion=" + anyoPublicacion +
                ", temas=" + temas +
                '}';
    }
}
