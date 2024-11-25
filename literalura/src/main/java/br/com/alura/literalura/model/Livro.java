package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToMany(mappedBy = "livros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;
    @Enumerated(EnumType.STRING)
    private Categoria idioma;
    private int numeroDownloads;

    public Livro(DadosLivro livro) {
        this.titulo = livro.titulo();
        this.autores = livro.autores().stream().map(d -> new Autor(d)).collect(Collectors.toUnmodifiableList());
        for(Autor autor: autores) {
           autor.addLivro(this);
        }
        this.idioma = Categoria.fromTipo(livro.idioma().get(0));
        this.numeroDownloads = livro.numeroDownloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Livro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutor(List<Autor> autores) {
        this.autores = autores;
    }

    public Categoria getIdioma() {
        return idioma;
    }

    public void setIdioma(Categoria idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(int numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        String autoria = autores.stream().map(a -> a.getNome()).collect(Collectors.joining(","));
        return "********** Livro **********" + '\n' +
                "Titulo: " + titulo + '\n' +
                "Autor: " + autoria + '\n' +
                "Idioma: " + idioma + '\n' +
                "Numero de downloads: " + numeroDownloads + '\n' +
                "***************************";
    }
}
