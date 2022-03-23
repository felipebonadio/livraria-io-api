package dev.db.livrariaio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.model.Autor;
import dev.db.livrariaio.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/{autorId}")
    public ResponseEntity<AutorDTO> getAutor(@PathVariable Long autorId) {
        AutorDTO autorToGet = autorService.findAutorById(autorId);
        return ResponseEntity.ok(autorToGet);
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> getAutores() {
        List<AutorDTO> autores = this.autorService.findAllAutores();
        return ResponseEntity.ok(autores);
    }

    @PostMapping
    public ResponseEntity<AutorDTO> saveAutor(@RequestBody @Valid AutorDTO autorDTO) {
        return new ResponseEntity<>(this.autorService.saveAutor(autorDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AutorDTO> updateAutor(@RequestBody AutorDTO autorDTO) {
        AutorDTO autorToUpdate = autorService.updateAutor(autorDTO);
        return ResponseEntity.ok(autorToUpdate);
    }

    @DeleteMapping("/{autorId}")
    public ResponseEntity<Autor> deleteById(@PathVariable Long autorId) {
        this.autorService.deleteAutor(autorId);
        return ResponseEntity.noContent().build();
    }
}
