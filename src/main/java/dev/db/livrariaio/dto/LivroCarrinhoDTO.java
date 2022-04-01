package dev.db.livrariaio.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LivroCarrinhoDTO {

    private Long id;

    private String titulo;

    private BigDecimal preco;

    private String capa;

}
