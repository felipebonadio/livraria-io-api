package dev.db.livrariaio.controller.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.db.livrariaio.controller.EnderecoController;
import dev.db.livrariaio.exception.DomainBusinessException;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.model.Endereco;
import dev.db.livrariaio.model.Pessoa;
import dev.db.livrariaio.service.EnderecoService;
import dev.db.livrariaio.service.PessoaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static dev.db.livrariaio.LivrariaFactory.criarEndereco;
import static dev.db.livrariaio.LivrariaFactory.criarPessoa;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Deve retornar um endereco por ID")
    void deveRetornarEnderecoPorId() throws Exception {
        when(enderecoService.findEnderecoById(1L)).thenReturn(criarEndereco());
        String endereceEsperada = mockMvc.perform(get("/enderecos/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(endereceEsperada, mapper.writeValueAsString(criarEndereco()));
    }

    @Test
    @DisplayName("Deve retornar NotFoundException ao pesquisar um endereco por ID e não encontra-lo")
    void deveRetornarNotFoundExceptionAoPesquisarEnderecoPorIdENaoEncontrar() throws Exception {
        when(enderecoService.findEnderecoById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/enderecos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma lista de enderecos")
    void deveRetornarListaDeEnderecos() throws Exception {
        List<Endereco> pessoas = List.of(criarPessoa());
        when(enderecoService.findAllPessoas()).thenReturn(pessoas);
        String listaEsperada = mockMvc.perform(get("/pessoas"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(listaEsperada, mapper.writeValueAsString(pessoas));
    }

    @Test
    @DisplayName("Deve salvar uma pessoa")
    void deveSalvarUmAutor() throws Exception {
        when(enderecoService.savePessoa(criarPessoa())).thenReturn(criarPessoa());
        String salvarPessoa = mapper.writeValueAsString(criarPessoa());
        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salvarPessoa))
                .andExpect(content().json(salvarPessoa))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar DomainBusinessException ao salvar uma pessoa sem nome")
    void deveRetornarDomainBusinessExceptionAoSalvarUmaPessoaSemNome() throws Exception {
        doThrow(new DomainBusinessException("")).when(enderecoService).savePessoa(criarPessoa());
        String salvarPessoaVazio = mapper.writeValueAsString(criarPessoa());
        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salvarPessoaVazio))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Deve atualizar uma pessoa")
    void deveAtualizarUmaPessoa() throws Exception {
        when(enderecoService.updatePessoa(criarPessoa())).thenReturn(criarPessoa());
        String atualizarPessoa = mapper.writeValueAsString(criarPessoa());
        mockMvc.perform(put("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atualizarPessoa))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar NotFoundException ao tentar atualziar uma pessoa e não encnotra-lo.")
    void deveRetornarNotFoundExceptionAoTentarAtualizarUmaPessoaENaoEncontrar() throws Exception {
        when(enderecoService.updatePessoa(criarPessoa())).thenThrow(NotFoundException.class);
        String pessoaNaoEncontrado = mapper.writeValueAsString(criarPessoa());
        mockMvc.perform(put("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pessoaNaoEncontrado))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve apagar uma pessoa por ID")
    void deveApagarPessoaPorId() throws Exception {
        when(enderecoService.findPessoaById(1L)).thenReturn(criarPessoa());
        mockMvc.perform(delete("/pessoas/1"))
                .andExpect(status().isNoContent());
        verify(enderecoService, times(1)).deletePessoa(criarPessoa().getId());
    }

    @Test
    @DisplayName("Deve retornar Not Found ao tentar apagar uma pessoa por ID e não encontrar")
    void deveRetornarNotFoundAoTentarApagarPessoaPorIdENaoEncontrar() throws Exception {
        doThrow(new NotFoundException("")).when(enderecoService).deletePessoa(5L);
        mockMvc.perform(delete("/pessoas/5"))
                .andExpect(status().isNotFound());
    }

}

}
