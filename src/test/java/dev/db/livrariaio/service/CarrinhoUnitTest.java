package dev.db.livrariaio.service;

import static dev.db.livrariaio.LivrariaFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.db.livrariaio.dto.CarrinhoDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.CarrinhoMapper;
import dev.db.livrariaio.mapper.ItemMapper;
import dev.db.livrariaio.model.Carrinho;
import dev.db.livrariaio.model.Item;
import dev.db.livrariaio.repository.CarrinhoRepository;

@ExtendWith(MockitoExtension.class)
public class CarrinhoUnitTest {

    @Mock
    private CarrinhoRepository carrinhoRepository;
     
    @InjectMocks
    private CarrinhoService carrinhoService;

    @Test
    @DisplayName("Deve retornar um carrinho por ID")
    void deveRetornarUmCarrinhoPorId() {
        CarrinhoDTO carrinhoDTO = CarrinhoMapper.carrinhoToDTO(criarCarrinho());
        when(carrinhoRepository.findById(1L)).thenReturn(Optional.of(criarCarrinho()));
        assertEquals(carrinhoDTO, carrinhoService.findCarrinhoById(1L));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao não achar um carrinho por Id")
    void deveRetornoNotFoundExceptionAoNaoAcharCarrinhoPorId() {
        when(carrinhoRepository.findById(any())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> carrinhoService.findCarrinhoById(any()));
    }

    @Test
    @DisplayName("Deve criar um carrinho")
    void deveCriarUmCarrinho() {
        when(carrinhoRepository.save(any())).thenReturn(criarCarrinho());
        assertEquals(criarCarrinho(), CarrinhoMapper.dtoToCarrinho(carrinhoService.saveCarrinho(criarCarrinhoDTO())));
    }

    @Test
    @DisplayName("Deve adicionar um item ao carrinho")
    void deveAdicionarUmItem() {
        Carrinho carrinhoVazio = criarCarrinho();
        Item item = criarItem();
        Carrinho carrinhoComItem = criarCarrinho();
        carrinhoComItem.adicionarItem(item);

        when(carrinhoRepository.findById(1L)).thenReturn(Optional.of(carrinhoVazio));
        when(carrinhoRepository.save(any())).thenReturn(carrinhoComItem);

        assertEquals(CarrinhoMapper.carrinhoToDTO(carrinhoComItem), carrinhoService.adicionarItemCarrinho(1L, ItemMapper.itemToDTO(item)));
    }

    @Test
    @DisplayName("Deve remover um item do carrinho")
    void deveRemoverUmItemDoCarrinho() {
        Carrinho carrinhoVazio = criarCarrinho();
        Item item = criarItem();
        Carrinho carrinhoComItem = criarCarrinho();
        carrinhoComItem.adicionarItem(item);
        Carrinho carrinhoComItemRemovido = criarCarrinho();

        when(carrinhoRepository.findById(1L)).thenReturn(Optional.of(carrinhoVazio));
        when(carrinhoRepository.save(any())).thenReturn(carrinhoComItemRemovido);

        assertEquals(CarrinhoMapper.carrinhoToDTO(carrinhoComItemRemovido),
                carrinhoService.removerItemCarrinho(1L, ItemMapper.itemToDTO(item)));
    }

    @Test
    @DisplayName("Deve retornar preço do item no carrinho ")
    void deveRetornarUmItemComValor50() {
        CarrinhoDTO carrinhoDTO = criarCarrinhoDTO();
        assertEquals(new BigDecimal("50.00"), carrinhoDTO.getPrecoTotal());
    }
}