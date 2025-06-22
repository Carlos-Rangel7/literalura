package com.literalura;

import com.literalura.dto.LivroDTO;
import com.literalura.model.Livro;
import com.literalura.repository.LivroRepository;
import com.literalura.service.ConsumoGutendexAPI;
import com.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    private Scanner sc = new Scanner(System.in);

    @Autowired
    private LivroService livroService;

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar Livro pelo titulo
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    0 - sair
                    """;
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    buscaLivro();
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

}
