package com.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.dto.AutorDTO;
import com.literalura.dto.LivroDTO;
import com.literalura.model.Autor;
import com.literalura.model.Livro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LivroService {

    private final ConsumoGutendexAPI api;
    private final ObjectMapper mapper;
    private final LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public LivroService(ConsumoGutendexAPI api, LivroRepository livroRepository, AutorRepository autorRepository) {
        this.api = api;
        this.mapper = new ObjectMapper();
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional // Garante que todas as operações dentro do método ocorram na mesma transação
    public Optional<Livro> buscarELancarLivro(String titulo) {
        String json = api.buscarLivros(titulo);
        if (json == null || json.isEmpty()) return Optional.empty();

        try {
            JsonNode root = mapper.readTree(json);
            JsonNode primeiroResultado = root.get("results").get(0);
            LivroDTO dto = mapper.treeToValue(primeiroResultado, LivroDTO.class);
            Livro livro = new Livro(dto.titulo(),
                    dto.idiomas().isEmpty() ? "Desconhecido" : dto.idiomas().get(0),
                    dto.numeroDeDownloads());

            if (!dto.autores().isEmpty()) {
                AutorDTO autorDTO = dto.autores().get(0);

                Optional<Autor> autorExistente = autorRepository.findByNomeAutorIgnoreCase(autorDTO.nomeAutor());

                Autor autor;
                if (autorExistente.isPresent()) {
                    autor = autorRepository.save(autorExistente.get());
                } else {
                    autor = new Autor(autorDTO.nomeAutor(), autorDTO.anoNascimento(), autorDTO.anoFalecimento());
                    autor = autorRepository.save(autor);
                }
                livro.setAutores(List.of(autor));
                autor.getLivros().add(livro);
            }
            livroRepository.save(livro);
            return Optional.of(livro);
        } catch (Exception e) {
            System.out.println("Erro ao converter JSON: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Livro> listarTodosLivro() {
        return livroRepository.findAll();
    }

    public List<Livro> listaLivroPorIdioma(String idioma ) {
        List<Livro> livros = livroRepository.listaLivroIdioma(idioma);
        if(!livros.isEmpty()) {
            return livros;
        }else {
            System.out.println("Nenhum livro encontrado nesse idioma");
            return List.of();
        }
    }

    public List<LivroDTO> buscarTopLivrosMaisBaixados(int limite) {
        String json = api.buscarLivrosComParametros("sort=-download_count");
        try {
            JsonNode root = mapper.readTree(json);
            JsonNode results = root.get("results");
            return Stream.iterate(0, i -> i + 1)
                    .limit(Math.min(limite, results.size()))
                    .map(results::get)
                    .map(node -> {
                        try {
                            return mapper.treeToValue(node, LivroDTO.class);
                        } catch (Exception e) {
                            throw new RuntimeException("Erro ao converter JSON para LivroDTO", e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Erro ao processar JSON: " + e.getMessage());
            return List.of();
        }
    }
}
