package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "Detalle_libro")
public class DetalleLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "numero_paginas",nullable = false)
    private String numeroPaginas;

    @Column(nullable = false)
    private String resumen;

    public String getResumen() {
        return resumen;
    }

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(String numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
