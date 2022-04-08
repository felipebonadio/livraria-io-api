package dev.db.livrariaio.service;

import dev.db.livrariaio.dto.ItemDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.ItemMapper;
import dev.db.livrariaio.model.Item;
import dev.db.livrariaio.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.PersistenceException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static dev.db.livrariaio.LivrariaFactory.criarItem;
import static dev.db.livrariaio.LivrariaFactory.criarItemDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemUnitTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    @DisplayName("Deve retornar um item por ID")
    void deveRetornarUmItemPorId() {
        ItemDTO itemDTO = ItemMapper.itemToDTO(criarItem());
        when(itemRepository.findById(1L)).thenReturn(Optional.of(criarItem()));
        assertEquals(itemDTO, itemService.findItemById(1L));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao não achar um item por Id")
    void deveRetornoNotFoundExceptionAoNaoAcharItemPorId() {
        when(itemRepository.findById(any())).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class, () -> itemService.findItemById(any()));
    }

    @Test
    @DisplayName("Deve retornar uma lista de itens")
    void deveRetornarUmaListaDeItens() {
        List<Item> itens = List.of(criarItem());
        List<ItemDTO> itensDTO = List.of(criarItemDto());
        when(itemRepository.findAll()).thenReturn(itens);
        assertEquals(itensDTO, itemService.findAllItens());
    }

    @Test
    @DisplayName("Deve criar um item")
    void deveCriarUmItem() {
        when(itemRepository.save(any())).thenReturn(criarItem());
        assertEquals(criarItemDto(), itemService.saveItem(criarItemDto()));
    }

    @Test
    @DisplayName("Deve atualizar um item")
    void deveAtualizarUmItem() {
        Item item = criarItem();
        ItemDTO itemDTO = criarItemDto();
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(itemRepository.save(Objects.requireNonNull(item))).thenReturn(item);
        assertEquals(ItemMapper.itemToDTO(item), itemService.updateItem(itemDTO));
    }

    @Test
    @DisplayName("Deve retornar uma NotFoundException ao tentar atualizar um item e não encontra-lo")
    void deveRetornarNotFoundExceptionAoTentarAtualizarENaoEncontrar() {

        when(itemRepository.findById(1L)).thenThrow(NotFoundException.class);
        ItemDTO atualizar = ItemMapper.itemToDTO(criarItem());
        atualizar.setPrecoItem(new BigDecimal("59.00"));
        assertThrows(NotFoundException.class, () -> itemService.updateItem(atualizar));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao salvar um item nulo")
    void deveRetornarExceptionalAoCriarItem() {
        when(itemRepository.save(any())).thenThrow(PersistenceException.class);
        assertThrows(PersistenceException.class, () -> itemService.saveItem(criarItemDto()));
    }

    @Test
    @DisplayName("Deve apagar um item")
    void deveApagarUmItem() {
        criarItem().setId(1L);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(criarItem()));
        itemService.deleteItem(criarItem().getId());
        verify(itemRepository, times(1)).delete(criarItem());
    }
}