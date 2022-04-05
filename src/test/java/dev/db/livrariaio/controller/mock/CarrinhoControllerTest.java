package dev.db.livrariaio.controller.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.db.livrariaio.controller.CarrinhoController;
import dev.db.livrariaio.dto.CarrinhoDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.CarrinhoMapper;
import dev.db.livrariaio.mapper.ItemMapper;
import dev.db.livrariaio.model.Carrinho;
import dev.db.livrariaio.model.Item;
import dev.db.livrariaio.service.CarrinhoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static dev.db.livrariaio.LivrariaFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarrinhoController.class)
public class CarrinhoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarrinhoService carrinhoService;

    @Test
    @DisplayName("Deve retornar um carrinho por Id")
    void deveRetornarUmCarrinhoPorID() throws Exception{
        when(carrinhoService.findCarrinhoById(1L)).thenReturn(criarCarrinhoDTO());
        String carrinhoEsperado = mockMvc.perform(get("/carrinhos/1")).andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(carrinhoEsperado, new ObjectMapper().writeValueAsString(criarCarrinhoDTO()));
    }

    @Test
    @DisplayName("Deve retornar Not Found ao pesquisar um carrinho por Id")
    void deveRetornarNotFoundAoPesquisarCarrinhoPorId() throws Exception {
        when(carrinhoService.findCarrinhoById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/carrinhos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Deve retornar uma lista de carrinhos")
    void deveRetornarUmaListaDeCarrinhos() throws Exception {
        List<CarrinhoDTO> listaDeCarrinhos = List.of(criarCarrinhoDTO());
        when(carrinhoService.findAllCarrinhos()).thenReturn(listaDeCarrinhos);
        String listaEsperada = mockMvc.perform(get("/carrinhos"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals(listaEsperada, new ObjectMapper().writeValueAsString(listaDeCarrinhos));
    }

    @Test
    @DisplayName("Deve Salvar um carrinho")
    void deveSalvarUmCarrinho() throws Exception {
        when(carrinhoService.saveCarrinho(criarCarrinhoDTO())).thenReturn(criarCarrinhoDTO());
        String salvarCarrinho = new ObjectMapper().writeValueAsString(criarCarrinhoDTO());
        mockMvc.perform(post("/carrinhos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(salvarCarrinho))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        assertEquals(salvarCarrinho, new ObjectMapper().writeValueAsString(criarCarrinhoDTO()));
    }

    @Test
    @DisplayName("Deve adicionar um item ao carrinho")
    void deveAdicionarUmItemAoCarrinho() throws Exception{
        Carrinho carrinho = criarCarrinho();
        Item item = criarItem();
        CarrinhoDTO carrinhoDTO = CarrinhoMapper.carrinhoToDTO(carrinho);
        carrinhoDTO.setItensDTO(List.of(ItemMapper.itemToDTO(item)));
        when(carrinhoService.adicionarItemCarrinho(1L, ItemMapper.itemToDTO(item))).thenReturn(carrinhoDTO);
        String atualizarCarrinho = new ObjectMapper().writeValueAsString(carrinhoDTO);
        mockMvc.perform(put("/carrinhos/adicionar/" + criarCarrinho().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atualizarCarrinho))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(atualizarCarrinho, new ObjectMapper().writeValueAsString(carrinhoDTO));
    }

    @Test
    @DisplayName("Deve remover um item do carrinho")
    void deveRemoverUmItemDoCarrinho() throws  Exception {
        Item item = criarItem();
        Carrinho carrinhoVazio = criarCarrinho();
        Carrinho carrinhoComItem = criarCarrinho();
        carrinhoComItem.adicionarItem(item);

        when(carrinhoService.removerItemCarrinho(1L, ItemMapper.itemToDTO(item)))
                .thenReturn(CarrinhoMapper.carrinhoToDTO(carrinhoVazio));

        String carrinhoComItemRemovido = new ObjectMapper().writeValueAsString(carrinhoVazio);
        mockMvc.perform(put("/carrinhos/remover/" + criarCarrinho().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carrinhoComItemRemovido))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        assertEquals(carrinhoComItemRemovido, new ObjectMapper().writeValueAsString(carrinhoVazio));
    }
}