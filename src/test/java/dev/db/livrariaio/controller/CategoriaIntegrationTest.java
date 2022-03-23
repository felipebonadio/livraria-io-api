package dev.db.livrariaio.controller;

import dev.db.livrariaio.LivrariaIoApplication;
import dev.db.livrariaio.dto.CategoriaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = LivrariaIoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CategoriaIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/categorias");
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve persistir e encontrar uma categoria")
    public void devePersistirEncontrarUmaCategoria() {
        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        ResponseEntity<CategoriaDTO> responseEntity = this.restTemplate
                .postForEntity(baseUrl, categoria, CategoriaDTO.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(categoria, responseEntity.getBody());

        CategoriaDTO categoriaToFind = this.restTemplate.getForObject(baseUrl.concat("/{id}"), CategoriaDTO.class, 1);
        assertEquals("programacao", categoriaToFind.getNome());
    }
}
