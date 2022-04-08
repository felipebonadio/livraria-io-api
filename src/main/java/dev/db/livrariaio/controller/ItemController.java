package dev.db.livrariaio.controller;

import dev.db.livrariaio.dto.ItemDTO;
import dev.db.livrariaio.model.Item;
import dev.db.livrariaio.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
@CrossOrigin(exposedHeaders = "errors, content-type")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long itemId) {
        ItemDTO itemToGet = itemService.findItemById(itemId);
        return ResponseEntity.ok(itemToGet);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getItem() {
        List<ItemDTO> itens = this.itemService.findAllItens();
        return ResponseEntity.ok(itens);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(this.itemService.saveItem(itemDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO itemToUpdate = itemService.updateItem(itemDTO);
        return ResponseEntity.ok(itemToUpdate);
    }

    @PutMapping("/aumentar/{itemId}")
    public ResponseEntity<ItemDTO> aumentarQuantidadeLivro(@PathVariable Long itemId) {
        ItemDTO itemToUpdate = itemService.aumentarQuantidadeLivro(itemId);
        return ResponseEntity.ok(itemToUpdate);
    }

    @PutMapping("/diminuir/{itemId}")
    public ResponseEntity<ItemDTO> diminuirQuantidadeLivro(@PathVariable Long itemId) {
        ItemDTO itemToUpdate = itemService.diminuirQuantidadeLivro(itemId);
        return ResponseEntity.ok(itemToUpdate);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Item> deleteById(@PathVariable Long itemId) {
        this.itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
