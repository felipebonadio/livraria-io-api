package dev.db.livrariaio.controller;

import dev.db.livrariaio.dto.LivroDTO;
import dev.db.livrariaio.model.Livro;
import dev.db.livrariaio.service.LivroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
@CrossOrigin(exposedHeaders = "errors, content-type")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/{livroId}")
    public ResponseEntity<LivroDTO> getLivro(@PathVariable Long livroId) {
        LivroDTO livroToGet = livroService.findLivroById(livroId);
        return ResponseEntity.ok(livroToGet);
    }

    @GetMapping
    public ResponseEntity<Page<LivroDTO>> getLivros(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(this.livroService.findAllLivros(paging));
    }

    @GetMapping("/categorias/{categoria}")
    public ResponseEntity<Page<LivroDTO>> getLivrosByCategoria(@PathVariable String categoria,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(this.livroService.findLivroByCategoria(categoria, paging));
    }

    @GetMapping("/procurar/{busca}")
    public ResponseEntity<Page<LivroDTO>> getLivrosByTituloOrAutor(@PathVariable String busca,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(this.livroService.findByTituloOrAutorNome(busca, paging));
    }

    @PostMapping
    public ResponseEntity<LivroDTO> saveLivro(@RequestBody @Valid LivroDTO livroDTO) {
        return new ResponseEntity<>(this.livroService.saveLivro(livroDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LivroDTO> updateLivro(@RequestBody LivroDTO livroDTO) {
        LivroDTO livroToUpdate = livroService.updateLivro(livroDTO);
        return ResponseEntity.ok(livroToUpdate);
    }

    @DeleteMapping("/{livroId}")
    public ResponseEntity<Livro> deleteById(@PathVariable Long livroId) {
        this.livroService.deleteLivro(livroId);
        return ResponseEntity.noContent().build();
    }
}
