package dev.db.livrariaio.mapper;

import dev.db.livrariaio.dto.AutorDTO;
import dev.db.livrariaio.model.Autor;

public class AutorMapper {

    public static AutorDTO autorToDTO(Autor autor) {
        return AutorDTO.builder()
                .id(autor.getId())
                .nome(autor.getNome())
                .descricao(autor.getDescricao())
                .email(autor.getEmail())
                .dataCriacao(autor.getDataCriacao())
                .build();
    }

    public static Autor dtoToAutor(AutorDTO autorDTO) {
        return Autor.builder()
                .id(autorDTO.getId())
                .nome(autorDTO.getNome())
                .descricao(autorDTO.getDescricao())
                .email(autorDTO.getEmail())
                .dataCriacao(autorDTO.getDataCriacao())
                .build();
    }
}
