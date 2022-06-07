package dev.db.livrariaio.controller;

import dev.db.livrariaio.model.Pessoa;
import dev.db.livrariaio.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(exposedHeaders = "errors, content-type")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long pessoaId){
        return ResponseEntity.ok(pessoaService.findPessoaById(pessoaId));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getPessoas(){
        return ResponseEntity.ok(pessoaService.findAllPessoas());
    }

    @PostMapping
    public ResponseEntity<Pessoa> savePessoa(@RequestBody @Valid Pessoa pessoa){
        return new ResponseEntity<>(pessoaService.savePessoa(pessoa), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Pessoa> updatePessoa(@RequestBody Pessoa pessoa){
        return ResponseEntity.ok(pessoaService.updatePessoa(pessoa));
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> deletePessoa(Long pessoaId){
        this.pessoaService.deletePessoa(pessoaId);
        return ResponseEntity.noContent().build();
    }
}
