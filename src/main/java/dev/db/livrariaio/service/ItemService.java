package dev.db.livrariaio.service;

import dev.db.livrariaio.dto.ItemDTO;
import dev.db.livrariaio.exception.NotFoundException;
import dev.db.livrariaio.mapper.ItemMapper;
import dev.db.livrariaio.mapper.LivroCarrinhoMapper;
import dev.db.livrariaio.model.Item;
import dev.db.livrariaio.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDTO findItemById(Long id) {
        Optional<ItemDTO> itemToFind = itemRepository.findById(id).map(ItemMapper::itemToDTO);
        if (itemToFind.isEmpty()) {
            throw new NotFoundException("Item não encontrado.");
        }
        return itemToFind.get();
    }

    public List<ItemDTO> findAllItens() {
        return itemRepository.findAll().stream().map(ItemMapper::itemToDTO).toList();
    }

    public ItemDTO saveItem(ItemDTO itemDTO) {
        Item item = ItemMapper.dtoToItem(itemDTO);
        item.setId(null);
        return ItemMapper.itemToDTO(itemRepository.save(item));
    }

    public ItemDTO updateItem(ItemDTO itemDTO) {
        Optional<Item> itemToFind = itemRepository.findById(itemDTO.getId());
        if (itemToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o item com o ID: " + itemDTO.getId() + ", pois o mesmo não existe.");
        }
        Item itemToUpdate = itemToFind.get();
        itemToUpdate.setPrecoItem(requireNonNullElse(itemDTO.getPrecoItem(), itemToUpdate.getPrecoItem()));
        itemToUpdate.setLivro(requireNonNullElse(LivroCarrinhoMapper.dtoToLivro(itemDTO.getLivroCarrinhoDTO()),
                itemToUpdate.getLivro()));
        itemToUpdate.setQuantidadeDeLivros(
                requireNonNullElse(itemDTO.getQuantidadeDeLivros(), itemToUpdate.getQuantidadeDeLivros()));
        return ItemMapper.itemToDTO(itemRepository.save(itemToUpdate));
    }

    public ItemDTO aumentarQuantidadeLivro(Long itemId) {
        Optional<Item> itemToFind = itemRepository.findById(itemId);
        if (itemToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o item com o ID: " + itemId + ", pois o mesmo não existe.");
        }
        Item itemToUpdate = itemToFind.get();
        itemToUpdate.aumentarQuantidade();
        return ItemMapper.itemToDTO(itemRepository.save(itemToUpdate));
    }

    public ItemDTO diminuirQuantidadeLivro(Long itemId) {
        Optional<Item> itemToFind = itemRepository.findById(itemId);
        if (itemToFind.isEmpty()) {
            throw new NotFoundException(
                    "Não foi possível atualizar o item com o ID: " + itemId + ", pois o mesmo não existe.");
        }
        Item itemToUpdate = itemToFind.get();
        itemToUpdate.diminuirQuantidade();
        return ItemMapper.itemToDTO(itemRepository.save(itemToUpdate));
    }

    public void deleteItem(Long itemId) {
        Optional<Item> item = this.itemRepository.findById(itemId);
        if (item.isEmpty()) {
            throw new NotFoundException("Item não encontrado.");
        }
        itemRepository.delete(item.get());
    }
}