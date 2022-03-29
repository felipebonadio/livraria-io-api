package dev.db.livrariaio.controller.integration;

import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.dto.LivroDTO;
import dev.db.livrariaio.mapper.AutorMapper;
import dev.db.livrariaio.mapper.CategoriaMapper;
import dev.db.livrariaio.repository.AutorRepository;
import dev.db.livrariaio.repository.CategoriaRepository;
import dev.db.livrariaio.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LivroIntegrationTest extends BaseIT {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setUp() {

        baseUrl = String.format("%s:%s/livros", baseUrl, port);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve persistir e encontrar um livro")
    public void devePersistirEncontrarUmLivro() {
        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        autorRepository.save(AutorMapper.dtoToAutor(autor));

        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        categoriaRepository.save(CategoriaMapper.dtoToCategoria(categoria));

        LivroDTO livro = new LivroDTO(1L, "linguagem c", "capitulo 1 e capitulo 2",
                new BigDecimal("59"), "capa1", 360, "123", LocalDate.now(), categoria, autor);

        ResponseEntity<LivroDTO> responseLivro = this.restTemplate.postForEntity(baseUrl, livro, LivroDTO.class);
        assertEquals(201, responseLivro.getStatusCodeValue());
        assertEquals(livro, responseLivro.getBody());

        LivroDTO livroToFind = this.restTemplate.getForObject(baseUrl.concat("/{id}"), LivroDTO.class, 1);
        assertEquals(livro.getTitulo(), livroToFind.getTitulo());

        assertEquals(autor, livroToFind.getAutorDTO());
        assertEquals(categoria, livroToFind.getCategoriaDTO());
    }

    @Test
    @DisplayName("Deve persistir e remover um livro")
    public void devePersistirRemoverUmLivro() {
        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        autorRepository.save(AutorMapper.dtoToAutor(autor));

        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        categoriaRepository.save(CategoriaMapper.dtoToCategoria(categoria));

        LivroDTO livro = new LivroDTO(1L, "linguagem c", "capitulo 1 e capitulo 2",
                new BigDecimal("59"), "capa1", 360, "123", LocalDate.now(), categoria, autor);

        ResponseEntity<LivroDTO> responseLivro = this.restTemplate.postForEntity(baseUrl, livro, LivroDTO.class);
        assertEquals(201, responseLivro.getStatusCodeValue());
        assertEquals(livro, responseLivro.getBody());

        this.restTemplate.delete(baseUrl.concat("/{id}"), 1);
        ResponseEntity<LivroDTO> livroToFind = this.restTemplate
                .getForEntity(baseUrl.concat("/{id}"), LivroDTO.class, 1);

        assertEquals(HttpStatus.NOT_FOUND, livroToFind.getStatusCode());
        boolean livroExiste = livroRepository.existsById(1L);
        assertFalse(livroExiste);
    }

}
