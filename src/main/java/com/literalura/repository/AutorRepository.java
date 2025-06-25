package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNomeAutorIgnoreCase(String nomeAutor);

    @Query("""
    SELECT a FROM Autor a
    WHERE a.anoNascimento <= :ano
    AND (a.anoFalecimento IS NULL OR a.anoFalecimento > :ano)
""")
    List<Autor> buscarAutoresVivosNoAno(@Param("ano") Integer ano);
}
