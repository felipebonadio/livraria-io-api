package dev.db.livrariaio.controller.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.db.livrariaio.controller.EnderecoController;
import dev.db.livrariaio.exception.DomainBusinessException;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.model.Endereco;
import dev.db.livrariaio.service.EnderecoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static dev.db.livrariaio.LivrariaFactory.criarEndereco;

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

//    @Test
//    @DisplayName("Deve retornar um endereco por ID")
//    void deveRetornarEnderecoPorId() throws Exception {
//        when(enderecoService.findEnderecoById(1L)).thenReturn(criarEndereco());
//        String endereceEsperada = mockMvc.perform(get("/enderecos/1"))
//                .andExpect(status().isOk())
//                .andReturn().getResponse()
//                .getContentAsString();
//        assertEquals(endereceEsperada, mapper.writeValueAsString(criarEndereco()));
//    }

//    @Test
//    @DisplayName("Deve retornar NotFoundException ao pesquisar um endereco por ID e não encontra-lo")
//    void deveRetornarNotFoundExceptionAoPesquisarEnderecoPorIdENaoEncontrar() throws Exception {
//        when(enderecoService.findEnderecoById(anyLong())).thenThrow(NotFoundException.class);
//        mockMvc.perform(get("/enderecos/1"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Deve retornar uma lista de enderecos")
//    void deveRetornarListaDeEnderecos() throws Exception {
//        List<Endereco> enderecos = List.of(criarEndereco());
//        when(enderecoService.findAllEndereco()).thenReturn(enderecos);
//        String listaEsperada = mockMvc.perform(get("/enderecos"))
//                .andExpect(status().isOk())
//                .andReturn().getResponse()
//                .getContentAsString();
//        assertEquals(listaEsperada, mapper.writeValueAsString(enderecos));
//    }

    @Test
    @DisplayName("Deve salvar um endereco")
    void deveSalvarUmEndereco() throws Exception {
        when(enderecoService.saveEndereco(criarEndereco())).thenReturn(criarEndereco());
        String salvarEndereco = mapper.writeValueAsString(criarEndereco());
        mockMvc.perform(post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salvarEndereco))
                .andExpect(content().json(salvarEndereco))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve retornar DomainBusinessException ao salvar um endereco sem nome")
    void deveRetornarDomainBusinessExceptionAoSalvarUmEnderecoSemNome() throws Exception {
        doThrow(new DomainBusinessException("")).when(enderecoService).saveEndereco(criarEndereco());
        String salvarEnderecoVazio = mapper.writeValueAsString(criarEndereco());
        mockMvc.perform(post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(salvarEnderecoVazio))
                .andExpect(status().isUnprocessableEntity());
    }
//
//    @Test
//    @DisplayName("Deve atualizar um endereco")
//    void deveAtualizarUmEndereco() throws Exception {
//        when(enderecoService.updateEndereco(criarEndereco())).thenReturn(criarEndereco());
//        String atualizarEndereco = mapper.writeValueAsString(criarEndereco());
//        mockMvc.perform(put("/enderecos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(atualizarEndereco))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("Deve retornar NotFoundException ao tentar atualziar um endereco e não encontra-lo.")
//    void deveRetornarNotFoundExceptionAoTentarAtualizarUmEnderecoENaoEncontrar() throws Exception {
//        when(enderecoService.updateEndereco(criarEndereco())).thenThrow(NotFoundException.class);
//        String enderecoNaoEncontrado = mapper.writeValueAsString(criarEndereco());
//        mockMvc.perform(put("/enderecos")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(enderecoNaoEncontrado))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("Deve apagar um endereco por ID")
//    void deveApagarEnderecoPorId() throws Exception {
//        when(enderecoService.findEnderecoById(1L)).thenReturn(criarEndereco());
//        mockMvc.perform(delete("/enderecos/1"))
//                .andExpect(status().isNoContent());
//        verify(enderecoService, times(1)).deleteEndereco(criarEndereco().getId());
//    }
//
//    @Test
//    @DisplayName("Deve retornar Not Found ao tentar apagar um endereco por ID e não encontrar")
//    void deveRetornarNotFoundAoTentarApagarEnderecoPorIdENaoEncontrar() throws Exception {
//        doThrow(new NotFoundException("")).when(enderecoService).deleteEndereco(5L);
//        mockMvc.perform(delete("/enderecos/5"))
//                .andExpect(status().isNotFound());
//    }

}
