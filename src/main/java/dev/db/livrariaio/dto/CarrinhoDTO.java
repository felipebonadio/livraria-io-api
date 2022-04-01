package dev.db.livrariaio.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CarrinhoDTO {
    private Long id;

    private List<ItemDTO> itensDTO;

    private BigDecimal precoTotal;

}
