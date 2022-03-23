package dev.db.livrariaio.mapper;

import dev.db.livrariaio.dto.LivroDTO;
import dev.db.livrariaio.model.Livro;

public class LivroMapper {

    public static LivroDTO livroToDTO(Livro livro) {
        return LivroDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .sumario(livro.getSumario())
                .preco(livro.getPreco())
                .numeroPaginas(livro.getNumeroPaginas())
                .isbn(livro.getIsbn())
                .dataPublicacao(livro.getDataPublicacao())
                .capa(livro.getCapa())
                .categoriaDTO(CategoriaMapper.categoriaToDTO(livro.getCategoria()))
                .autorDTO(AutorMapper.autorToDTO(livro.getAutor()))
                .build();
    }

    public static Livro dtoToLivro(LivroDTO livroDTO) {
        return Livro.builder()
                .id(livroDTO.getId())
                .titulo(livroDTO.getTitulo())
                .sumario(livroDTO.getSumario())
                .preco(livroDTO.getPreco())
                .numeroPaginas(livroDTO.getNumeroPaginas())
                .isbn(livroDTO.getIsbn())
                .dataPublicacao(livroDTO.getDataPublicacao())
                .capa(livroDTO.getCapa())
                .categoria(CategoriaMapper.dtoToCategoria(livroDTO.getCategoriaDTO()))
                .autor(AutorMapper.dtoToAutor(livroDTO.getAutorDTO()))
                .build();
    }
}
