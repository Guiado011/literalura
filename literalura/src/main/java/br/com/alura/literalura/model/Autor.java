package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int dataNascimento;
    private int dataFalecimento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "autor_livro",  // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private List<Livro> livros = new ArrayList<>();

    public Autor(DadosAutor autor) {
        this.nome = autor.nome();
        this.dataNascimento = autor.dataNascimento();
        this.dataFalecimento = autor.dataFalecimento();
    }

    public Autor() {
    }

    public Autor(String nome, int dataNascimento, int dataFalecimento, List<Livro> livros) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataFalecimento = dataFalecimento;
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(int dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getDataFalecimento() {
        return dataFalecimento;
    }

    public void setDataFalecimento(int dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public void addLivro(Livro livro) {
        if(!livros.contains(livro)) {
            livros.add(livro);
        }
    }

    @Override
    public String toString() {
        String livraria = livros.stream().map(l -> l.getTitulo()).collect(Collectors.joining(","));
        return '\n' +
                "Autor:" + nome + '\n' +
                "Ano de nascimento: " + dataNascimento + '\n' +
                "Ano de falecimento: " + dataFalecimento + '\n' +
                "Livros: " + livraria + '\n';
    }
}
