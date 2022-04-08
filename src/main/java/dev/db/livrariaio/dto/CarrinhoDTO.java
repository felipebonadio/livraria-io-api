package dev.db.livrariaio.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CarrinhoDTO {
    private Long id;

    private List<ItemDTO> itensDTO = new ArrayList<>();

    private BigDecimal precoTotal;

    public BigDecimal somarPrecoTotal() {
        return itensDTO.stream().map(ItemDTO::getPrecoItem).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CarrinhoDTO(Long id, List<ItemDTO> itensDTO, BigDecimal precoTotal) {
        this.id = id;
        this.itensDTO = itensDTO;
        this.precoTotal = this.somarPrecoTotal();
    }

    public CarrinhoDTO(Long id) {
        this.id = id;
    }
}