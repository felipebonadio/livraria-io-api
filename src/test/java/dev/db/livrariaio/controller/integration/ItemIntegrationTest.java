package dev.db.livrariaio.controller.integration;

import dev.db.livrariaio.dto.*;
import dev.db.livrariaio.mapper.*;
import dev.db.livrariaio.model.Item;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ItemIntegrationTest extends BaseIT {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @BeforeEach
    public void setUp() {

        baseUrl = String.format("%s:%s/itens", baseUrl, port);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Deve persistir e encontrar um item")
    public void devePersistirEncontrarUmItem() {

        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        autorRepository.save(AutorMapper.dtoToAutor(autor));

        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        categoriaRepository.save(CategoriaMapper.dtoToCategoria(categoria));

        Livro livro = new Livro(1L, "linguagem c", "capitulo 1 e capitulo 2",
                new BigDecimal("59"), 230, "ISBN", LocalDate.now(), "capa", CategoriaMapper.dtoToCategoria(categoria),
                AutorMapper.dtoToAutor(autor));

        livroRepository.save(livro);

        CarrinhoDTO carrinho = new CarrinhoDTO(1L);
        carrinhoRepository.save(CarrinhoMapper.dtoToCarrinho(carrinho));

        Item item = new Item(1L, CarrinhoMapper.dtoToCarrinho(carrinho), livro, 1, new BigDecimal("50.00"));

        ItemDTO itemDTO = ItemMapper.itemToDTO(item);
        ResponseEntity<ItemDTO> responseItem = this.restTemplate.postForEntity(baseUrl, itemDTO, ItemDTO.class);
        assertEquals(201, responseItem.getStatusCodeValue());
        assertEquals(itemDTO.getId(), responseItem.getBody().getId());

        ItemDTO itemToFind = this.restTemplate.getForObject(baseUrl.concat("/{id}"), ItemDTO.class, 1);
        assertEquals(itemDTO.getId(), itemToFind.getId());

    }

    @Test
    @DisplayName("Deve persistir e remover um item")
    public void devePersistirRemoverUmItem() {
        AutorDTO autor = new AutorDTO(1L, "guilherme", "guilherme@email.com", "autor renomado", LocalDate.now());
        autorRepository.save(AutorMapper.dtoToAutor(autor));

        CategoriaDTO categoria = new CategoriaDTO(1L, "programacao", "descricao tal");
        categoriaRepository.save(CategoriaMapper.dtoToCategoria(categoria));

        Livro livro = new Livro(1L, "linguagem c", "capitulo 1 e capitulo 2",
                new BigDecimal("59"), 230, "ISBN", LocalDate.now(), "capa", CategoriaMapper.dtoToCategoria(categoria),
                AutorMapper.dtoToAutor(autor));

        livroRepository.save(livro);

        CarrinhoDTO carrinho = new CarrinhoDTO(1L);
        carrinhoRepository.save(CarrinhoMapper.dtoToCarrinho(carrinho));

        Item item = new Item(1L, CarrinhoMapper.dtoToCarrinho(carrinho), livro, 1, new BigDecimal("50.00"));

        ItemDTO itemDTO = ItemMapper.itemToDTO(item);
        ResponseEntity<ItemDTO> responseItem = this.restTemplate.postForEntity(baseUrl, itemDTO, ItemDTO.class);
        assertEquals(201, responseItem.getStatusCodeValue());
        assertEquals(itemDTO.getId(), responseItem.getBody().getId());

        this.restTemplate.delete(baseUrl.concat("/{id}"), 1);
        ResponseEntity<ItemDTO> itemToFind = this.restTemplate
                .getForEntity(baseUrl.concat("/{id}"), ItemDTO.class, 1);

        assertEquals(HttpStatus.NOT_FOUND, itemToFind.getStatusCode());
        boolean itemExiste = itemRepository.existsById(1L);
        assertFalse(itemExiste);
    }
}
