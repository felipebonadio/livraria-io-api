package dev.db.livrariaio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.db.livrariaio.dto.CarrinhoDTO;
import dev.db.livrariaio.dto.ItemDTO;
import dev.db.livrariaio.service.CarrinhoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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

    @PutMapping("/adicionar")
    public ResponseEntity<CarrinhoDTO> adicionarItemCarrinho(@RequestBody CarrinhoDTO carrinhoDTO, ItemDTO itemDTO ) {
        CarrinhoDTO carrinhoToUpdate = carrinhoService.adicionarItemCarrinho(carrinhoDTO, itemDTO);
        return ResponseEntity.ok(carrinhoToUpdate);
    }

    @PutMapping("/remover")
    public ResponseEntity<CarrinhoDTO> removerItemCarrinho(@RequestBody CarrinhoDTO carrinhoDTO, ItemDTO itemDTO ) {
        CarrinhoDTO carrinhoToUpdate = carrinhoService.removerItemCarrinho(carrinhoDTO, itemDTO);
        return ResponseEntity.ok(carrinhoToUpdate);
    }

    @DeleteMapping("/{carrinhoId}")
    public ResponseEntity<CarrinhoDTO> deleteByCarrinhoId(@PathVariable Long carrinhoId) {
        this.carrinhoService.deleteCarrinho(carrinhoId);
        return ResponseEntity.noContent().build();
    }



}
