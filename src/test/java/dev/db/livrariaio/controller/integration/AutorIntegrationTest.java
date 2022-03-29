package dev.db.livrariaio.controller.integration;

import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.springframework.http.HttpStatus;

public class AutorIntegrationTest extends BaseIT {

    @Autowired
    AutorRepository autorRepository;

    @BeforeEach
    public void setUp() {
        baseUrl = String.format("%s:%s/autores", baseUrl, port);
    }

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
        assertEquals(autor, autorToFind);
    }

    @Test
    @DisplayName("Deve persistir e remover e não encontrar um autor")
    public void devePersistirRemoverENaoEncontrar() {
        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        ResponseEntity<AutorDTO> responseEntity = this.restTemplate
                .postForEntity(baseUrl, autor, AutorDTO.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(autor, responseEntity.getBody());

        this.restTemplate.delete(baseUrl.concat("/{id}"), 1);
        ResponseEntity<AutorDTO> autorToFind = this.restTemplate
                .getForEntity(baseUrl.concat("/{id}"), AutorDTO.class, 1);

        assertEquals(HttpStatus.NOT_FOUND, autorToFind.getStatusCode());
        boolean autorExiste = autorRepository.existsById(1L);
        assertFalse(autorExiste);
    }
}
