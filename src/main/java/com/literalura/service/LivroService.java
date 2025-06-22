package com.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.dto.AutorDTO;
import com.literalura.dto.LivroDTO;
import com.literalura.model.Autor;
import com.literalura.model.Livro;
import com.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final ConsumoGutendexAPI api;
    private final ObjectMapper mapper;
    private final LivroRepository livroRepository;

    public LivroService(ConsumoGutendexAPI api, LivroRepository livroRepository) {
        this.api = api;
        this.mapper = new ObjectMapper();
        this.livroRepository = livroRepository;
    }

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
                Autor autor = new Autor(autorDTO.nomeAutor(), autorDTO.anoNascimento(), autorDTO.anoFalecimento());
                livro.setAutores(List.of(autor));
            }

            livroRepository.save(livro);
            return Optional.of(livro);

        } catch (Exception e) {
            System.out.println("Erro ao converter JSON: " + e.getMessage());
            return Optional.empty();
        }
    }

}
