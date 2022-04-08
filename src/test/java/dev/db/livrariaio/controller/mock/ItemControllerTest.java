package dev.db.livrariaio.controller.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.db.livrariaio.controller.ItemController;
import dev.db.livrariaio.dto.ItemDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static dev.db.livrariaio.LivrariaFactory.criarItemDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Deve retornar um item por ID")
    void deveRetornarItemPorId() throws Exception {
        when(itemService.findItemById(1L)).thenReturn(criarItemDto());
        String itemEsperado = mockMvc.perform(get("/itens/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(itemEsperado, new ObjectMapper().writeValueAsString(criarItemDto()));
    }

    @Test
    @DisplayName("Deve retornar NotFoundException ao pesquisar um item por ID e não encontra-lo")
    void deveRetornarNotFoundExceptionAoPesquisarItemPorIdENaoEncontrar() throws Exception {
        when(itemService.findItemById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/itens/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma lista de itens")
    void deveRetornarListaDeItens() throws Exception {
        List<ItemDTO> itens = List.of(criarItemDto());
        when(itemService.findAllItens()).thenReturn(itens);
        String listaEsperada = mockMvc.perform(get("/itens"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(listaEsperada, new ObjectMapper().writeValueAsString(itens));
    }

    @Test
    @DisplayName("Deve salvar um item")
    void deveSalvarUmItem() throws Exception {
        ItemDTO itemDTO = criarItemDto();
        when(itemService.saveItem(itemDTO)).thenReturn(itemDTO);
        String salvarItem = mapper.writeValueAsString(itemDTO);
        mockMvc.perform(post("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(salvarItem))
                .andExpect(content().json(salvarItem))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve atualizar um item")
    void deveAtualizarUmItem() throws Exception {
        when(itemService.updateItem(criarItemDto())).thenReturn(criarItemDto());
        String atualizarItem = mapper.writeValueAsString(criarItemDto());
        mockMvc.perform(put("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(atualizarItem))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar NotFoundException ao tentar atualizar um item e não encontra-lo.")
    void deveRetornarNotFoundExceptionAoTentarAtualizarUmItemENaoEncontrar() throws Exception {
        when(itemService.updateItem(criarItemDto())).thenThrow(NotFoundException.class);
        String itemNaoEncontrado = mapper.writeValueAsString(criarItemDto());
        mockMvc.perform(put("/itens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(itemNaoEncontrado))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve apagar um item por ID")
    void deveApagarItemPorId() throws Exception {
        when(itemService.findItemById(criarItemDto().getId())).thenReturn(criarItemDto());
        mockMvc.perform(delete("/itens/" + criarItemDto().getId()))
                .andExpect(status().isNoContent());
        verify(itemService, times(1)).deleteItem(criarItemDto().getId());
    }

    @Test
    @DisplayName("Deve retornar Not Found ao tentar apagar um item por ID e não encontrar")
    void deveRetornarNotFoundAoTentarApagarItemPorIdENaoEncontrar() throws Exception {
        doThrow(new NotFoundException("")).when(itemService).deleteItem(5L);
        mockMvc.perform(delete("/itens/5"))
                .andExpect(status().isNotFound());
    }
}