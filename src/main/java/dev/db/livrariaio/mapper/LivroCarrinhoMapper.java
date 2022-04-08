package dev.db.livrariaio.mapper;

import dev.db.livrariaio.dto.LivroCarrinhoDTO;
import dev.db.livrariaio.model.Livro;

public class LivroCarrinhoMapper {
    public static LivroCarrinhoDTO livroToDTO(Livro livro) {
        return LivroCarrinhoDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .preco(livro.getPreco())
                .capa(livro.getCapa())
                .build();
    }

    public static Livro dtoToLivro(LivroCarrinhoDTO livroCarrinhoDTO) {
        return Livro.builder()
                .id(livroCarrinhoDTO.getId())
                .titulo(livroCarrinhoDTO.getTitulo())
                .preco(livroCarrinhoDTO.getPreco())
                .capa(livroCarrinhoDTO.getCapa())
                .build();
    }
}
