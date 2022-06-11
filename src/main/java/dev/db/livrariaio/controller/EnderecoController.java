package dev.db.livrariaio.controller;

import dev.db.livrariaio.model.Endereco;
import dev.db.livrariaio.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(exposedHeaders = "errors, content-type")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody @Valid Endereco endereco){
        return new ResponseEntity<>(this.enderecoService.saveEndereco(endereco), HttpStatus.CREATED);
    }
}
