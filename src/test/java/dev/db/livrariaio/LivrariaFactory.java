package dev.db.livrariaio;

import dev.db.livrariaio.dto.*;
import dev.db.livrariaio.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LivrariaFactory {
    public static Categoria criarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("categoria");
        categoria.setDescricao("descricao");
        return categoria;
    }

    public static Autor criarAutor() {
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setDataCriacao(LocalDate.now());
        autor.setNome("nome");
        autor.setEmail("email");
        autor.setDescricao("descricao");
        return autor;
    }

    public static Livro criarLivro() {
        Livro livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("titulo");
        livro.setNumeroPaginas(50);
        livro.setIsbn("isbn");
        livro.setSumario("sumario");
        livro.setPreco(new BigDecimal("50.00"));
        livro.setCapa("capa");
        livro.setDataPublicacao(LocalDate.now());
        livro.setCategoria(criarCategoria());
        livro.setAutor(criarAutor());
        return livro;
    }

    public static CategoriaDTO criarCategoriaDto() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(1L);
        categoriaDTO.setNome("categoria");
        categoriaDTO.setDescricao("descricao");
        return categoriaDTO;
    }

    public static AutorDTO criarAutorDto() {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setId(1L);
        autorDTO.setDataCriacao(LocalDate.now());
        autorDTO.setNome("nome");
        autorDTO.setEmail("email");
        autorDTO.setDescricao("descricao");
        return autorDTO;
    }

    public static LivroDTO criarLivroDto() {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(1L);
        livroDTO.setTitulo("titulo");
        livroDTO.setNumeroPaginas(50);
        livroDTO.setIsbn("isbn");
        livroDTO.setSumario("sumario");
        livroDTO.setPreco(new BigDecimal("50.00"));
        livroDTO.setCapa("capa");
        livroDTO.setDataPublicacao(LocalDate.now());
        livroDTO.setCategoriaDTO(criarCategoriaDto());
        livroDTO.setAutorDTO(criarAutorDto());
        return livroDTO;
    }

    public static LivroCarrinhoDTO criarLivroCarrinhoDTO() {
        LivroCarrinhoDTO livroCarrinhoDTO = new LivroCarrinhoDTO();
        livroCarrinhoDTO.setId(1L);
        livroCarrinhoDTO.setTitulo("titulo");
        livroCarrinhoDTO.setPreco(new BigDecimal("50.00"));
        livroCarrinhoDTO.setCapa("capa");
        return livroCarrinhoDTO;
    }

    public static Item criarItem() {
        Item item = new Item();
        item.setId(1L);
        item.setLivro(criarLivro());
        item.setQuantidadeDeLivros(1);
        item.setPrecoItem(new BigDecimal("50.00"));
        return item;
    }

    public static ItemDTO criarItemDto() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(1L);
        itemDTO.setLivroCarrinhoDTO(criarLivroCarrinhoDTO());
        itemDTO.setQuantidadeDeLivros(1);
        itemDTO.setPrecoItem(new BigDecimal("50.00"));
        return itemDTO;
    }

    public static CarrinhoDTO criarCarrinhoDTO() {
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
        carrinhoDTO.setId(1L);
        carrinhoDTO.setItensDTO(List.of(criarItemDto()));
        carrinhoDTO.setPrecoTotal(carrinhoDTO.somarPrecoTotal());
        return carrinhoDTO;
    }

    public static Carrinho criarCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinho.setId(1L);
        return carrinho;
    }
}
