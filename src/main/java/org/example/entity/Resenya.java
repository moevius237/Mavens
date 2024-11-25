package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "resenya")
public class Resenya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String contenido;
    private int puntuacion;

    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "libro_id" ,nullable = false)
    private Libro libro;

    public Libro getLibro() {
        return libro;
    }


    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
