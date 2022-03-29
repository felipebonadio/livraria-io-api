package dev.db.livrariaio.controller.integration;

import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CategoriaIntegrationTest extends BaseIT {

    @Autowired
    CategoriaRepository categoriaRepository;

    @BeforeEach
    public void setUp() {
        baseUrl = String.format("%s:%s/categorias", baseUrl, port);
    }

    @Test
    @DisplayName("Deve persistir e encontrar uma categoria")
    public void devePersistirEncontrarUmaCategoria() {
        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        ResponseEntity<CategoriaDTO> responseEntity = this.restTemplate
                .postForEntity(baseUrl, categoria, CategoriaDTO.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(categoria, responseEntity.getBody());

        CategoriaDTO categoriaToFind = this.restTemplate.getForObject(baseUrl.concat("/{id}"), CategoriaDTO.class, 1);
        assertEquals(categoria, categoriaToFind);
    }

    @Test
    @DisplayName("Deve persistir e remover uma categoria")
    public void devePersistirRemoverUmaCategoria() {
        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        ResponseEntity<CategoriaDTO> responseEntity = this.restTemplate
                .postForEntity(baseUrl, categoria, CategoriaDTO.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(categoria, responseEntity.getBody());

        this.restTemplate.delete(baseUrl.concat("/{id}"), 1);
        ResponseEntity<CategoriaDTO> categoriaToFind = this.restTemplate
                .getForEntity(baseUrl.concat("/{id}"), CategoriaDTO.class, 1);

        assertEquals(HttpStatus.NOT_FOUND, categoriaToFind.getStatusCode());
        boolean categoriaExiste = categoriaRepository.existsById(1L);
        assertFalse(categoriaExiste);
    }
}
