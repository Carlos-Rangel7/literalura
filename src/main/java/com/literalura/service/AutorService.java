package com.literalura.service;

import com.literalura.model.Autor;
import com.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;


    public List<Autor> listaAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> buscarAutoresVivosNoAno(int ano) {
        List<Autor> autores =  autorRepository.buscarAutoresVivosNoAno(ano);
        if(!autores.isEmpty()) {
            return autores;
        } else {
            System.out.println("Nenhum autor encontrado nesse periodo");
            return List.of();
        }
    }
}
