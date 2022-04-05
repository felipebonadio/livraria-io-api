package dev.db.livrariaio.controller.mock;

import static dev.db.livrariaio.LivrariaFactory.criarLivroDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.db.livrariaio.controller.LivroController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import dev.db.livrariaio.dto.LivroDTO;
import dev.db.livrariaio.exception.DomainBusinessException;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.service.LivroService;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private LivroService livroService;

        ObjectMapper mapper = new ObjectMapper();

        @Test
        @DisplayName("Deve retornar um livro por ID")
        void deveRetornarLivroPorId() throws Exception {
                when(livroService.findLivroById(1L)).thenReturn(criarLivroDto());
                String livroEsperado = mockMvc.perform(get("/livros/1")).andExpect(status().isOk())
                                .andReturn().getResponse()
                                .getContentAsString();
                assertEquals(livroEsperado, new ObjectMapper().writeValueAsString(criarLivroDto()));
        }

        @Test
        @DisplayName("Deve retornar Not Found ao pesquisar um livro por ID")
        void deveRetornarNotFoundAoPesquisarLivroPorId() throws Exception {
                when(livroService.findLivroById(anyLong())).thenThrow(NotFoundException.class);
                mockMvc.perform(get("/livros/1"))
                                .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Deve retornar uma lista de livros")
        void deveRetornarListaDeLivros() throws Exception {
                List<LivroDTO> lista = List.of(criarLivroDto());
                Page<LivroDTO> livros = new PageImpl(lista, PageRequest.of(0, 1), 1);
                when(livroService.findAllLivros(PageRequest.of(0, 10))).thenReturn(livros);
                String listaEsperada = mockMvc.perform(get("/livros"))
                                .andExpect(status().isOk())
                                .andReturn().getResponse()
                                .getContentAsString();
                assertEquals(listaEsperada, new ObjectMapper().writeValueAsString(livros));
        }

        @Test
        @DisplayName("Deve retornar uma lista de livros filtradas por categoria")
        void deveRetornarListaDeLivrosPorCategoria() throws Exception {
                List<LivroDTO> lista = List.of(criarLivroDto());
                Page<LivroDTO> livros = new PageImpl(lista, PageRequest.of(0, 1), 1);
                when(livroService.findLivroByCategoria("categoria", PageRequest.of(0, 10))).thenReturn(livros);
                String esperado = mockMvc.perform(get("/livros/categorias/categoria"))
                                .andExpect(status().isOk())
                                .andReturn().getResponse()
                                .getContentAsString();
                assertEquals(esperado, new ObjectMapper().writeValueAsString(livros));
        }

        @Test
        @DisplayName("Deve retornar uma lista de livros filtradas por autor ou por título")
        void deveRetornarListaDeLivrosPorAutorNomeOuTitulo() throws Exception {
                List<LivroDTO> lista = List.of(criarLivroDto());
                Page<LivroDTO> livros = new PageImpl(lista, PageRequest.of(0, 1), 1);
                when(livroService.findByTituloOrAutorNome("nome", PageRequest.of(0, 10))).thenReturn(livros);
                String esperado = mockMvc.perform(get("/livros/procurar/nome"))
                                .andExpect(status().isOk())
                                .andReturn().getResponse()
                                .getContentAsString();
                assertEquals(esperado, new ObjectMapper().writeValueAsString(livros));
        }

        @Test
        @DisplayName("Deve salvar um livro")
        void deveSalvarUmLivro() throws Exception {
                when(livroService.saveLivro(criarLivroDto())).thenReturn(criarLivroDto());
                String salvarLivro = mapper.writeValueAsString(criarLivroDto());
                mockMvc.perform(post("/livros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(salvarLivro))
                                .andExpect(content().json(salvarLivro))
                                .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("Deve retornar DomainBusinessException ao salvar um livro sem título")
        void deveRetornarDomainBusinessExceptionAoSalvarUmLivro() throws Exception {
                LivroDTO erroAoSalvarLivros = new LivroDTO();
                when(livroService.saveLivro(erroAoSalvarLivros)).thenThrow(DomainBusinessException.class);
                String salvarLivroVazio = mapper.writeValueAsString(erroAoSalvarLivros);
                mockMvc.perform(post("/livros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(salvarLivroVazio))
                                .andExpect(status().isUnprocessableEntity());
        }

        @Test
        @DisplayName("Deve atualizar um livro")
        void deveAtualizarUmLivro() throws Exception {
                when(livroService.updateLivro(criarLivroDto())).thenReturn(criarLivroDto());
                String atualizarLivro = mapper.writeValueAsString(criarLivroDto());
                mockMvc.perform(put("/livros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(atualizarLivro))
                                .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Deve apagar um livro por ID")
        void deveApagarLivroPorId() throws Exception {
                when(livroService.findLivroById(criarLivroDto().getId())).thenReturn(criarLivroDto());
                mockMvc.perform(delete("/livros/" + criarLivroDto().getId()))
                                .andExpect(status().isNoContent());
                verify(livroService, times(1)).deleteLivro(criarLivroDto().getId());
        }

        @Test
        @DisplayName("Deve retornar Not Found ao tentar apagar um livro por ID e não encontrar")
        void deveRetornarNotFoundAoTentarApagarLivroPorIdENaoEncontrar() throws Exception {
                doThrow(new NotFoundException("")).when(livroService).deleteLivro(5L);
                mockMvc.perform(delete("/livros/" + 5L))
                                .andExpect(status().isNotFound());
        }
}