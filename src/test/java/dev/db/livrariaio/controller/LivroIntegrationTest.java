package dev.db.livrariaio.controller;

import dev.db.livrariaio.LivrariaIoApplication;
import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.dto.LivroDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LivrariaIoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class LivroIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/livros");
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve persistir e encontrar um livro")
    public void devePersistirEncontrarUmLivro() {
        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        LivroDTO livro = new LivroDTO(1L, "linguagem c", "capitulo 1 e capitulo 2",
                new BigDecimal("59"), "capa1", 360, "123", LocalDate.now(), categoria, autor);
        this.restTemplate.postForEntity("http://localhost:" + port + "/autores", autor, AutorDTO.class);
        this.restTemplate.postForEntity("http://localhost:" + port + "/categorias", categoria, CategoriaDTO.class);

        ResponseEntity<LivroDTO> responseLivro = this.restTemplate.postForEntity(baseUrl, livro, LivroDTO.class);
        assertEquals(201, responseLivro.getStatusCodeValue());
        assertEquals(livro, responseLivro.getBody());

        LivroDTO livroToFind = this.restTemplate.getForObject(baseUrl.concat("/{id}"), LivroDTO.class, 1);
        assertEquals("linguagem c", livroToFind.getTitulo());
        assertEquals(autor, livroToFind.getAutorDTO());
        assertEquals(categoria, livroToFind.getCategoriaDTO());
    }
}
