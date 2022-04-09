package dev.db.livrariaio.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LivroDTO {

    private Long id;

    private String titulo;

    private String sumario;

    private BigDecimal preco;

    private String capa;

    private Integer numeroPaginas;

    private String isbn;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dataPublicacao;

    private CategoriaDTO categoriaDTO;

    private AutorDTO autorDTO;
}
