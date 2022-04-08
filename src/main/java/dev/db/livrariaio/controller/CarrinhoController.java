package dev.db.livrariaio.controller;

import dev.db.livrariaio.dto.CarrinhoLocalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.db.livrariaio.dto.CarrinhoDTO;
import dev.db.livrariaio.dto.ItemDTO;
import dev.db.livrariaio.service.CarrinhoService;

import java.util.List;

@RestController
@RequestMapping("/carrinhos")
@CrossOrigin(exposedHeaders = "errors, content-type")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/{carrinhoId}")
    public ResponseEntity<CarrinhoDTO> getItem(@PathVariable Long carrinhoId) {
        CarrinhoDTO carrinhoToGet = carrinhoService.findCarrinhoById(carrinhoId);
        return ResponseEntity.ok(carrinhoToGet);
    }

    @GetMapping()
    public ResponseEntity<List<CarrinhoDTO>> getItens() {
        List<CarrinhoDTO> carrinhos = this.carrinhoService.findAllCarrinhos();
        return ResponseEntity.ok(carrinhos);
    }

    @PostMapping
    public ResponseEntity<CarrinhoDTO> saveItem(@RequestBody CarrinhoDTO carrinhoDTO) {
        return new ResponseEntity<>(this.carrinhoService.saveCarrinho(carrinhoDTO), HttpStatus.CREATED);
    }

    @PostMapping("/criar")
    public ResponseEntity<CarrinhoLocalDTO> criarCarrinho() {
        return new ResponseEntity<>(this.carrinhoService.criarCarrinho(), HttpStatus.CREATED);
    }

    @PutMapping("/adicionar/{carrinhoId}")
    public ResponseEntity<CarrinhoDTO> adicionarItemCarrinho(@PathVariable Long carrinhoId,
            @RequestBody ItemDTO itemDTO) {
        CarrinhoDTO carrinhoToUpdate = carrinhoService.adicionarItemCarrinho(carrinhoId, itemDTO);
        return ResponseEntity.ok(carrinhoToUpdate);
    }

    @PutMapping("/remover/{carrinhoId}")
    public ResponseEntity<CarrinhoDTO> removerItemCarrinho(@PathVariable Long carrinhoId,
            @RequestBody ItemDTO itemDTO) {
        CarrinhoDTO carrinhoToUpdate = carrinhoService.removerItemCarrinho(carrinhoId, itemDTO);
        return ResponseEntity.ok(carrinhoToUpdate);
    }

    @DeleteMapping("/{carrinhoId}")
    public ResponseEntity<CarrinhoDTO> deleteByCarrinhoId(@PathVariable Long carrinhoId) {
        this.carrinhoService.deleteCarrinho(carrinhoId);
        return ResponseEntity.noContent().build();
    }
}