package dev.db.livrariaio.controller.integration;

import dev.db.livrariaio.dto.*;
import dev.db.livrariaio.mapper.*;
import dev.db.livrariaio.model.Carrinho;
import dev.db.livrariaio.model.Livro;
import dev.db.livrariaio.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CarrinhoIntegrationTest extends BaseIT {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @BeforeEach
    public void setUp() {

        baseUrl = String.format("%s:%s/carrinhos", baseUrl, port);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve persistir e encontrar um carrinho")
    public void devePersistirEncontrarUmCarrinho() {

        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        autorRepository.save(AutorMapper.dtoToAutor(autor));

        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        categoriaRepository.save(CategoriaMapper.dtoToCategoria(categoria));

        Livro livro = new Livro(1L, "linguagem c", "capitulo 1 e capitulo 2",
                new BigDecimal("59.00"), 230, "ISBN", LocalDate.now(), "capa",
                CategoriaMapper.dtoToCategoria(categoria), AutorMapper.dtoToAutor(autor));
        livroRepository.save(livro);

        Carrinho carrinho = new Carrinho(1L);

        CarrinhoDTO carrinhoDTO = CarrinhoMapper.carrinhoToDTO(carrinho);

        ResponseEntity<CarrinhoDTO> responseCarrinho = this.restTemplate.postForEntity(baseUrl, carrinhoDTO,
                CarrinhoDTO.class);

        assertEquals(201, responseCarrinho.getStatusCodeValue());
        assertEquals(carrinhoDTO, responseCarrinho.getBody());

        CarrinhoDTO carrinhoToFind = this.restTemplate.getForObject(baseUrl.concat("/{id}"), CarrinhoDTO.class, 1);
        assertEquals(responseCarrinho.getBody(), carrinhoToFind);
    }

    @Test
    @DisplayName("Deve persistir e remover um carrinho")
    public void devePersistirRemoverUmCarrinho() {
        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        autorRepository.save(AutorMapper.dtoToAutor(autor));

        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        categoriaRepository.save(CategoriaMapper.dtoToCategoria(categoria));

        Livro livro = new Livro(1L, "linguagem c", "capitulo 1 e capitulo 2",
                new BigDecimal("59.00"), 230, "ISBN", LocalDate.now(), "capa",
                CategoriaMapper.dtoToCategoria(categoria), AutorMapper.dtoToAutor(autor));
        livroRepository.save(livro);

        Carrinho carrinho = new Carrinho(1L);

        CarrinhoDTO carrinhoDTO = CarrinhoMapper.carrinhoToDTO(carrinho);

        ResponseEntity<CarrinhoDTO> responseCarrinho = this.restTemplate.postForEntity(baseUrl, carrinhoDTO,
                CarrinhoDTO.class);

        assertEquals(201, responseCarrinho.getStatusCodeValue());
        assertEquals(carrinhoDTO, responseCarrinho.getBody());

        this.restTemplate.delete(baseUrl.concat("/{id}"), 1);
        ResponseEntity<CarrinhoDTO> carrinhoToFind = this.restTemplate
                .getForEntity(baseUrl.concat("/{id}"), CarrinhoDTO.class, 1);

        assertEquals(HttpStatus.NOT_FOUND, carrinhoToFind.getStatusCode());
        boolean carrinhoExiste = carrinhoRepository.existsById(1L);
        assertFalse(carrinhoExiste);
    }

}
