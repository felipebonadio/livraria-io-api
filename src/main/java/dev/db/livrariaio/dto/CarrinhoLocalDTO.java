package dev.db.livrariaio.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CarrinhoLocalDTO {
    private Long id;

    public CarrinhoLocalDTO(Long id) {
        this.id = id;
    }
}
