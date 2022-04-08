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

    private Integer quantidadeDeLivros;

    private BigDecimal precoItem;

    public ItemDTO(Long id, LivroCarrinhoDTO livroCarrinhoDTO, Integer quantidadeDeLivros, BigDecimal precoItem) {
        this.id = id;
        this.livroCarrinhoDTO = livroCarrinhoDTO;
        this.quantidadeDeLivros = quantidadeDeLivros;
        this.precoItem = livroCarrinhoDTO.getPreco().multiply(new BigDecimal(this.getQuantidadeDeLivros()));
    }
}