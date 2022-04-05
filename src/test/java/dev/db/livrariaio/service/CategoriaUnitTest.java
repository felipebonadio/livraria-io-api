package dev.db.livrariaio.service;

import static dev.db.livrariaio.LivrariaFactory.criarCategoria;
import static dev.db.livrariaio.LivrariaFactory.criarCategoriaDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.CategoriaMapper;
import dev.db.livrariaio.model.Categoria;
import dev.db.livrariaio.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
public class CategoriaUnitTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    @DisplayName("Deve retornar uma categoria por ID")
    void deveRetornarUmaCategoriaPorId() {
        CategoriaDTO categoriaDTO = CategoriaMapper.categoriaToDTO(criarCategoria());
        when(categoriaRepository.findById(1L)).thenReturn(Optional.ofNullable(criarCategoria()));
        assertEquals(categoriaDTO, categoriaService.findCategoriaById(1L));
    }

    @Test
    @DisplayName("Deve retornar um erro ao não achar uma categoria por Id")
    void deveRetornoNotFoundExceptionAoNaoAcharCategoriaPorId() {
        when(categoriaRepository.findById(2L)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> categoriaService.findCategoriaById(2L));
    }

    @Test
    @DisplayName("Deve retornar uma lista de categorias")
    void deveRetornarUmaListaDeCategoria() {
        List<CategoriaDTO> categoriasDTO = List.of(criarCategoriaDto());
        List<Categoria> categorias = List.of(criarCategoria());
        when(categoriaRepository.findAll()).thenReturn(categorias);
        assertEquals(categoriasDTO, categoriaService.findAllCategorias());
    }

    @Test
    @DisplayName("Deve criar uma categoria")
    void deveCriarUmaCategoria() {
        when(categoriaRepository.save(any())).thenReturn(criarCategoria());
        assertEquals(criarCategoriaDto(), categoriaService.saveCategoria(criarCategoriaDto()));
    }

    @Test
    @DisplayName("Deve atualizar uma categoria")
    void deveAtualizarUmaCategoria() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.ofNullable(criarCategoria()));
        when(categoriaRepository.save(criarCategoria())).thenReturn(criarCategoria());
        assertEquals(criarCategoriaDto(), categoriaService.updateCategoria(criarCategoriaDto()));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao tentar atualizar uma categoria e não encontra-la")
    void deveRetornarNotFoundAoProcurarCategoriaAtualizarENaoEncontrar() {
        when(categoriaRepository.findById(1L)).thenThrow(NotFoundException.class);
        CategoriaDTO atualizar = CategoriaMapper.categoriaToDTO(criarCategoria());
        assertThrows(NotFoundException.class, () -> categoriaService.updateCategoria(atualizar));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao salvar uma categoria nulo")
    void deveRetornarExceptionalAoCriarCategoria() {
        when(categoriaRepository.save(any())).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> categoriaService.saveCategoria(criarCategoriaDto()));
    }

    @Test
    @DisplayName("Deve apagar uma categoria")
    void deveApagarUmaCategoria() {
        criarCategoria().setId(1L);
        when(categoriaRepository.findById(any())).thenReturn(Optional.of(criarCategoria()));
        categoriaService.deleteCategoria(1L);
        verify(categoriaRepository, times(1)).delete(criarCategoria());
    }
}