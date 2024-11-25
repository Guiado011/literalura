package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Categoria;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("select l from Livro l")
    List<Livro> buscarLivros();

    @Query("select a from Autor a")
    List<Autor> buscarAutores();

    @Query("select a from Autor a where a.dataNascimento <= :ano and a.dataFalecimento >= :ano")
    List<Autor> buscarAutoresPorAno(int ano);

    @Query("select l from Livro l where l.idioma = :idioma")
    List<Livro> buscarLivrosPorIdioma(Categoria idioma);
}
