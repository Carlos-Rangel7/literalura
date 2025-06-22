package com.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private int numeroDeDownloads;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    public Livro(){}
    public Livro(String titulo, String idioma, int numeroDeDownloads) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.numeroDeDownloads = numeroDeDownloads;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(int numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    @Override
    public String toString() {
        String nomeAutor = (autores != null && !autores.isEmpty()) ? autores.get(0).getNomeAutor() : "Desconhecido";
        return """
            *** LIVRO ***
            Autor : %s
            Titulo: %s
            Idioma: %s
            Downloads: %d
            """.formatted(autores, titulo, idioma, numeroDeDownloads);
    }
}
