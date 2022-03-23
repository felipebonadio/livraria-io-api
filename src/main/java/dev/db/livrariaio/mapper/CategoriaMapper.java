package dev.db.livrariaio.mapper;

import dev.db.livrariaio.dto.CategoriaDTO;
import dev.db.livrariaio.model.Categoria;

public class CategoriaMapper {

    public static CategoriaDTO categoriaToDTO(Categoria categoria) {
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }

    public static Categoria dtoToCategoria(CategoriaDTO categoriaDTO) {
        return Categoria.builder()
                .id(categoriaDTO.getId())
                .nome(categoriaDTO.getNome())
                .descricao(categoriaDTO.getDescricao())
                .build();
    }
}
