package dev.db.livrariaio.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.model.Categoria;
import dev.db.livrariaio.service.CategoriaService;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(exposedHeaders = "errors, content-type")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable Long categoriaId) {
        CategoriaDTO categoriaToGet = categoriaService.findCategoriaById(categoriaId);
        return ResponseEntity.ok(categoriaToGet);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getCategorias() {
        List<CategoriaDTO> categorias = this.categoriaService.findAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> saveCategoria(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        return new ResponseEntity<>(this.categoriaService.saveCategoria(categoriaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoriaDTO> updateCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaToUpdate = categoriaService.updateCategoria(categoriaDTO);
        return ResponseEntity.ok(categoriaToUpdate);
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Categoria> deleteById(@PathVariable Long categoriaId) {
        this.categoriaService.deleteCategoria(categoriaId);
        return ResponseEntity.noContent().build();
    }
}
