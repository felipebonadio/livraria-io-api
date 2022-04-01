package dev.db.livrariaio.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class ItemDTO {

    private Long id;

    private LivroCarrinhoDTO livroCarrinhoDTO;

    private int quantidadeDeLivros;

    private BigDecimal precoItem;

    public ItemDTO(Long id, LivroCarrinhoDTO livroCarrinhoDTO, int quantidadeDeLivros, BigDecimal precoItem) {
        this.id = id;
        this.livroCarrinhoDTO = livroCarrinhoDTO;
        this.quantidadeDeLivros = 1;
        this.precoItem = livroCarrinhoDTO.getPreco();
    }
}
