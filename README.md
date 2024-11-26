# Literalura

**Literalura** é um projeto desenvolvido como parte de um desafio da Alura, com o objetivo de permitir que os usuários busquem livros através da API **Gutendex**, visualizem livros cadastrados no banco de dados e explorem informações sobre autores. Quando um livro é buscado, ele é automaticamente registrado no banco de dados, assim como o autor.

O projeto foi desenvolvido utilizando **Spring Framework** em Java para o backend e **PostgreSQL** como banco de dados.

## Funcionalidades

- **Busca de Livros na API Gutendex**: O usuário pode buscar livros diretamente na API **Gutendex**. Os resultados da busca são exibidos com informações detalhadas sobre o livro e o autor.
- **Cadastro Automático de Livros e Autores**: Quando um livro é encontrado na busca, ele é automaticamente registrado no banco de dados, junto com o autor, caso ainda não estejam cadastrados.
- **Catálogo de Livros**: Visualize todos os livros cadastrados no banco de dados, com detalhes como título, autor, descrição e ano de publicação.
- **Catálogo de Autores**: Acesse uma lista de autores cujos livros estão registrados na plataforma, com informações adicionais sobre eles.
- **Detalhes do Livro**: Ao visualizar um livro no catálogo, o usuário pode ver detalhes como título, autor, idioma e o número de downloads.

## Tecnologias Utilizadas

- **Backend**: [Spring Framework](https://spring.io/) (Java)
- **Banco de Dados**: [PostgreSQL](https://www.postgresql.org/)
- **API Externa**: [Gutendex API](https://gutendex.com/) (para busca de livros e autores)

## Como Rodar o Projeto Localmente

Siga as instruções abaixo para configurar o projeto em sua máquina:

### Clone o repositório

```bash
git clone https://github.com/Guiado011/literalura.git
