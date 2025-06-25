package com.literalura.main;

import com.literalura.dto.LivroDTO;
import com.literalura.model.Autor;
import com.literalura.model.Livro;
import com.literalura.repository.LivroRepository;
import com.literalura.service.AutorService;
import com.literalura.service.ConsumoGutendexAPI;
import com.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner sc = new Scanner(System.in);

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorservice;

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar Livro pelo titulo
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    6 - Buscar top livros mais baixados
                    0 - sair
                    """;
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    buscaLivro();
                    break;

                case 2:
                    listarTodosLivros();
                    break;

                case 3:
                    listarAutoresRegistrados();
                    break;

                case 4:
                    listaAutoresEmDeterminadoAno();
                    break;

                case 5:
                    listarLivrosEmDeterminadoIdioma();
                    break;

                case 6:
                    buscarTopLivros();
                    break;

                case 0:
                    System.out.println("Encerrando o programa");
                    break;

                default:
                    System.out.println("Digite uma opção valida");
            }
        }

    }

    private void buscaLivro() {
        var cadastraLivro = "s";
        while (cadastraLivro.equalsIgnoreCase("s")) {
            System.out.println("Digite o título do livro: ");
            var titulo = sc.nextLine();

            livroService.buscarELancarLivro(titulo)
                    .ifPresentOrElse(
                            l -> System.out.println("""
                                \n*** LIVRO ***
                                Título: %s
                                Autor: %s
                                Idioma: %s
                                Downloads: %d
                                """.formatted(
                                    l.getTitulo(),
                                    l.getAutores().isEmpty() ? "Autor desconhecido" : l.getAutores().get(0),
                                    l.getIdioma(),
                                    l.getNumeroDeDownloads()
                            )),
                            () -> System.out.println("Livro não encontrado ou erro na busca.")
                    );
            System.out.println("Deseja buscar outro livro? (S/N)");
            cadastraLivro = sc.nextLine();
        }
    }

    private void listarTodosLivros() {
        List<Livro> livros = livroService.listarTodosLivro();
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorservice.listaAutores();
        autores.forEach(a -> {
            System.out.printf(
                    """
                            \nAutor: %s
                            Nascimento: %s
                            Falecimento: %s 
                            """,
                    a.getNomeAutor(),
                    a.getAnoNascimento(),
                    a.getAnoFalecimento() == null ? "Vivo" : a.getAnoFalecimento()
            );
            System.out.println("Livros:");
            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()) );
            System.out.println("------------------------------");
        });
    }

    public void listaAutoresEmDeterminadoAno() {
        System.out.println("Digite o ano para listar os autores vivo nesse periodo: ");
        var ano = sc.nextInt();
        sc.nextLine();
        List<Autor> autores =  autorservice.buscarAutoresVivosNoAno(ano);

        autores.forEach(a -> {
            System.out.printf(
                    """
                            \nAutor: %s
                            Ano de Nascimento: %s
                            Ano de Falecimento: %s 
                            """,
                    a.getNomeAutor(),
                    a.getAnoNascimento(),
                    a.getAnoFalecimento() == null ? "Vivo" : a.getAnoFalecimento()
            );
            System.out.println("Livros: ");
            a.getLivros().forEach(l -> System.out.println(" - " + l.getTitulo()));
            System.out.println("------------------------------");
        } );
    }

    public void listarLivrosEmDeterminadoIdioma() {
        System.out.println(
                """
                Insira um idioma para realizar a busca: 
                pt - portugues
                es - espanhol
                fr - frances
                en - ingles
                """
        );
        var idioma = sc.nextLine();
        List<Livro> livros = livroService.listaLivroPorIdioma(idioma);
        livros.forEach(l -> {
            System.out.printf("""
                    \nLivro: %s
                    Autor: %s
                    Idioma: %s
                    """,
                    l.getTitulo(),
                    l.getAutores(),
                    l.getIdioma()
            );
        } );
    }

    private void buscarTopLivros() {
        System.out.println("Digite quantos livros deseja ver (max 10): ");
        int qtd = sc.nextInt();
        sc.nextLine();

        if (qtd < 1 || qtd > 10) {
            System.out.println("Número inválido, mostrando top 10.");
            qtd = 10;
        }

        List<LivroDTO> topLivros = livroService.buscarTopLivrosMaisBaixados(qtd);

        if (topLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        System.out.println("\n*** Top Livros Mais Baixados ***");
        for (LivroDTO livro : topLivros) {
            System.out.printf("""
                    Título    : %s
                    Autor     : %s
                    Idioma    : %s
                    Downloads : %d
                    ----------------------------
                    """,
                    livro.titulo(),
                    livro.autores().isEmpty() ? "Autor desconhecido" : livro.autores().get(0).nomeAutor(),
                    livro.idiomas().isEmpty() ? "Idioma desconhecido" : livro.idiomas().get(0),
                    livro.numeroDeDownloads());
        }
    }


}
