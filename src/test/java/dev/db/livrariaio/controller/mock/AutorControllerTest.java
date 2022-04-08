package dev.db.livrariaio.controller.mock;

import static dev.db.livrariaio.LivrariaFactory.criarAutorDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.db.livrariaio.controller.AutorController;
import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.exception.DomainBusinessException;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.service.AutorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutorController.class)
public class AutorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AutorService autorService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Deve retornar um autor por ID")
    void deveRetornarAutorPorId() throws Exception {
        when(autorService.findAutorById(1L)).thenReturn(criarAutorDto());
        String autorEsperado = mockMvc.perform(get("/autores/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(autorEsperado, mapper.writeValueAsString(criarAutorDto()));
    }

    @Test
    @DisplayName("Deve retornar NotFoundException ao pesquisar um autor por ID e não encontra-lo")
    void deveRetornarNotFoundExceptionAoPesquisarAutorPorIdENaoEncontrar() throws Exception {
        when(autorService.findAutorById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/autores/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma lista de autores")
    void deveRetornarListaDeAutores() throws Exception {
        List<AutorDTO> autores = List.of(criarAutorDto());
        when(autorService.findAllAutores()).thenReturn(autores);
        String listaEsperada = mockMvc.perform(get("/autores"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(listaEsperada, mapper.writeValueAsString(autores));
    }

    @Test
    @DisplayName("Deve salvar um autor")
    void deveSalvarUmAutor() throws Exception {
        when(autorService.saveAutor(criarAutorDto())).thenReturn(criarAutorDto());
        String salvarAutor = mapper.writeValueAsString(criarAutorDto());
        mockMvc.perform(post("/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(salvarAutor))
                .andExpect(content().json(salvarAutor))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar DomainBusinessException ao salvar um autor sem nome")
    void deveRetornarDomainBusinessExceptionAoSalvarUmAutorSemNome() throws Exception {
        doThrow(new DomainBusinessException("")).when(autorService).saveAutor(criarAutorDto());
        String salvarAutorVazio = mapper.writeValueAsString(criarAutorDto());
        mockMvc.perform(post("/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(salvarAutorVazio))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Deve atualizar um autor")
    void deveAtualizarUmAutor() throws Exception {
        when(autorService.updateAutor(criarAutorDto())).thenReturn(criarAutorDto());
        String atualizarAutor = mapper.writeValueAsString(criarAutorDto());
        mockMvc.perform(put("/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(atualizarAutor))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar NotFoundException ao tentar atualziar um autor e não encnotra-lo.")
    void deveRetornarNotFoundExceptionAoTentarAtualizarUmAutorENaoEncontrar() throws Exception {
        when(autorService.updateAutor(criarAutorDto())).thenThrow(NotFoundException.class);
        String autorNaoEncontrado = mapper.writeValueAsString(criarAutorDto());
        mockMvc.perform(put("/autores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(autorNaoEncontrado))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve apagar um autor por ID")
    void deveApagarAutorPorId() throws Exception {
        when(autorService.findAutorById(criarAutorDto().getId())).thenReturn(criarAutorDto());
        mockMvc.perform(delete("/autores/" + criarAutorDto().getId()))
                .andExpect(status().isNoContent());
        verify(autorService, times(1)).deleteAutor(criarAutorDto().getId());
    }

    @Test
    @DisplayName("Deve retornar Not Found ao tentar apagar um autor por ID e não encontrar")
    void deveRetornarNotFoundAoTentarApagarAutorPorIdENaoEncontrar() throws Exception {
        doThrow(new NotFoundException("")).when(autorService).deleteAutor(5L);
        mockMvc.perform(delete("/autores/5"))
                .andExpect(status().isNotFound());
    }
}