package dev.db.livrariaio.service;

import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.AutorMapper;
import dev.db.livrariaio.model.Autor;
import dev.db.livrariaio.repository.AutorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static dev.db.livrariaio.LivrariaFactory.criarAutor;
import static dev.db.livrariaio.LivrariaFactory.criarAutorDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutorUnitTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @Test
    @DisplayName("Deve retornar um autor por ID")
    void deveRetornarUmAutorPorId() {
        AutorDTO autorDTO = AutorMapper.autorToDTO(criarAutor());
        when(autorRepository.findById(1L)).thenReturn(Optional.of(criarAutor()));
        assertEquals(autorDTO, autorService.findAutorById(1L));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao não achar um autor por Id")
    void deveRetornarNotFoundExceptionAoNaoAcharAutorPorId() {
        when(autorRepository.findById(2L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> autorService.findAutorById(2L));
    }

    @Test
    @DisplayName("Deve retornar uma lista de autores")
    void deveRetornarUmaListaDeAutores() {
        List<Autor> autores = List.of(criarAutor());
        List<AutorDTO> autoresDTO = List.of(criarAutorDto());
        when(autorRepository.findAll()).thenReturn(autores);
        assertEquals(autoresDTO, autorService.findAllAutores());
    }

    @Test
    @DisplayName("Deve criar um autor")
    void deveCriarUmAutor() {
        when(autorRepository.save(any())).thenReturn(criarAutor());
        assertEquals(criarAutorDto(), autorService.saveAutor(criarAutorDto()));
    }

    @Test
    @DisplayName("Deve atualizar um autor")
    void deveAtualizarUmAutor() {
        when(autorRepository.findById(1L)).thenReturn(Optional.of(criarAutor()));
        when(autorRepository.save(criarAutor())).thenReturn(criarAutor());
        assertEquals(AutorMapper.autorToDTO(criarAutor()), autorService.updateAutor(criarAutorDto()));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao tentar atualizar um autor e não encontra-lo")
    void deveRetornarNotFoundExceptionAoTentarAtualizarENaoEncontrar() {

        when(autorRepository.findById(1L)).thenThrow(NotFoundException.class);
        AutorDTO atualizar = AutorMapper.autorToDTO(criarAutor());
        atualizar.setNome("Felipe");
        assertThrows(NotFoundException.class, () -> autorService.updateAutor(atualizar));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao salvar um autor nulo")
    void deveRetornarExceptionalAoCriarAutor() {
        when(autorRepository.save(any())).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> autorService.saveAutor(criarAutorDto()));
    }

    @Test
    @DisplayName("Deve apagar um autor")
    void deveApagarUmAutor() {
        criarAutor().setId(1L);
        when(autorRepository.findById(1L)).thenReturn(Optional.of(criarAutor()));
        autorService.deleteAutor(criarAutor().getId());
        verify(autorRepository, times(1)).delete(criarAutor());
    }
}