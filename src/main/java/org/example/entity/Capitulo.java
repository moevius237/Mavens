package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "capitulo")
public class Capitulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "numero")
    private String numero;

    @Column(name = "numero_paginas")
    private int numeroPaginas;

    public String getNumero() {
        return numero;
    }

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libroId;

    public Libro getLibroId() {
        return libroId;
    }

    public void setLibroId(Libro libroId) {
        this.libroId = libroId;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
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
}
