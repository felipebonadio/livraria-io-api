package dev.db.livrariaio.controller.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.db.livrariaio.controller.CategoriaController;
import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.exception.DomainBusinessException;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.service.CategoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static dev.db.livrariaio.LivrariaFactory.criarCategoriaDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Deve retornar uma categoria por ID")
    void deveRetornarCategoriaPorId() throws Exception {
        when(categoriaService.findCategoriaById(1L)).thenReturn(criarCategoriaDto());
        String categoriaEsperado = mockMvc.perform(get("/categorias/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(categoriaEsperado, mapper.writeValueAsString(criarCategoriaDto()));
    }

    @Test
    @DisplayName("Deve retornar Not Found ao pesquisar uma categoria por ID")
    void deveRetornarNotFoundExcpetionAoPesquisarCategoriaPorIdENaoEncontrar() throws Exception {
        when(categoriaService.findCategoriaById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/categorias/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma lista de categorias")
    void deveRetornarListaDeCategorias() throws Exception {
        List<CategoriaDTO> categorias = List.of(criarCategoriaDto());
        when(categoriaService.findAllCategorias()).thenReturn(categorias);
        String listaEsperada = mockMvc.perform(get("/categorias"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(listaEsperada, mapper.writeValueAsString(categorias));
    }

    @Test
    @DisplayName("Deve salvar uma categoria")
    void deveSalvarUmaCategoria() throws Exception {
        when(categoriaService.saveCategoria(criarCategoriaDto())).thenReturn(criarCategoriaDto());
        String salvarCategoria = mapper.writeValueAsString(criarCategoriaDto());
        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(salvarCategoria))
                .andExpect(content().json(salvarCategoria))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar erro ao salvar uma categoria já existente")
    void deveRetornarBadRequestAoSalvarUmaCategoriaRepetida() throws Exception {
        CategoriaDTO erroAoSalvarCategoria = new CategoriaDTO();
        when(categoriaService.saveCategoria(erroAoSalvarCategoria)).thenThrow(DomainBusinessException.class);
        String salvarCategoriaVazia = mapper.writeValueAsString(erroAoSalvarCategoria);
        mockMvc.perform(post("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(salvarCategoriaVazia))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Deve atualizar uma categoria")
    void deveAtualizarUmaCategoria() throws Exception {
        when(categoriaService.updateCategoria(criarCategoriaDto()))
                .thenReturn(criarCategoriaDto());
        String atualizarCategoria = mapper.writeValueAsString(criarCategoriaDto());
        mockMvc.perform(put("/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(atualizarCategoria))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve apagar uma categoria por ID")
    void deveApagarCategoriaPorId() throws Exception {
        when(categoriaService.findCategoriaById(criarCategoriaDto().getId())).thenReturn(criarCategoriaDto());
        mockMvc.perform(delete("/categorias/" + criarCategoriaDto().getId()))
                .andExpect(status().isNoContent());
        verify(categoriaService, times(1)).deleteCategoria(criarCategoriaDto().getId());
    }

    @Test
    @DisplayName("Deve retornar Not Found ao tentar apagar uma categoria por ID e não encontrar")
    void deveRetornarNotFoundAoTentarApagarCategoriaPorIdENaoEncontrar() throws Exception {
        doThrow(new NotFoundException("")).when(categoriaService).deleteCategoria(5L);
        mockMvc.perform(delete("/categorias/" + 5L))
                .andExpect(status().isNotFound());
    }
}