package dev.db.livrariaio.controller;

import dev.db.livrariaio.LivrariaIoApplication;
import dev.db.livrariaio.dto.AutorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LivrariaIoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AutorIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/autores");
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve persistir e encontrar um autor")
    public void devePersistirEncontrarUmAutor() {
        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        ResponseEntity<AutorDTO> responseEntity = this.restTemplate
                .postForEntity(baseUrl, autor, AutorDTO.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(autor, responseEntity.getBody());

        AutorDTO autorToFind = this.restTemplate
                .getForObject(baseUrl.concat("/{id}"), AutorDTO.class, 1);
        assertEquals("guilherme", autorToFind.getNome());
    }
}
