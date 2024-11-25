package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumirAPI;
import br.com.alura.literalura.service.ConverteDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    ConsumirAPI consumo = new ConsumirAPI();
    ConverteDados conversor = new ConverteDados();
    final String URL_BASICA = "https://gutendex.com/books/?search=";

    public List<Livro> livros = new ArrayList<>();
    public List<Autor> autores = new ArrayList<>();

    public LivroRepository repository;

    public Principal(LivroRepository repository) {
        this.repository = repository;
    }

    public void exibirMenu() throws IOException, InterruptedException {
        int opcao = -1;
        while(opcao != 0) {
            System.out.println("""
                    Escolha a opção desejada:
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    0 - Sair
                    """);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch(opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    buscarAutoresContemporaneos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivro() throws IOException, InterruptedException {
        System.out.println("Qual o título do livro que deseja buscar?");
        String tituloLivro = leitura.nextLine();
        String respostaJson = consumo.obterDados(URL_BASICA + tituloLivro.toLowerCase().replace(" ","+"));
        ResultadosLivro resultadosLivro = conversor.obterDados(respostaJson, ResultadosLivro.class);
        DadosLivro dadosLivro = resultadosLivro.livro().get(0);
        Livro livro = new Livro(dadosLivro);
        //livros.add(livro);
        //livro.getAutores().stream().forEach( a -> autores.add(a));
        repository.save(livro);
        System.out.println(livro);
    }

    private void buscarAutores() {
        List<Autor> autoresBuscados = repository.buscarAutores();
        if(autoresBuscados.size() > 0) {
            autoresBuscados.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor registrado ainda");
        }
    }

    private void listarLivros() {

        List<Livro> livrosBuscados = repository.buscarLivros();

        if(livrosBuscados.size() > 0) {
            livrosBuscados.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro registrado ainda");
        }
    }

    private void buscarAutoresContemporaneos() {
        System.out.println("Qual ano deseja pesquisar?");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autoresBuscadosPorAno = repository.buscarAutoresPorAno(ano);
        //List<Autor> autoresContemporaneos = autores.stream().filter(a -> a.getDataFalecimento() >= ano && a.getDataNascimento() <= ano).collect(Collectors.toList());
        if(autoresBuscadosPorAno.size() > 0) {
            autoresBuscadosPorAno.forEach(System.out::println);
        } else {
            System.out.println("Nenhum autor vivo nesta época!");
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                \n
                Insira o idioma para realizar a busca:
                
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                
                """);

        String categoria = leitura.nextLine();
        Categoria idioma = Categoria.fromTipo(categoria);

        List<Livro> livrosBuscadosPorIdioma = repository.buscarLivrosPorIdioma(idioma);
        //List<Livro> livrosCategoricos = livros.stream()
                //.filter( l -> l.getIdioma() == Categoria.fromTipo(categoria))
                //.collect(Collectors.toList());

        if(livrosBuscadosPorIdioma.size() > 0) {
            livrosBuscadosPorIdioma.forEach(System.out::println);
        } else {
            System.out.println("Nenhum livro com esta categoria registrado no Banco de Dados!");
        }
    }
}


