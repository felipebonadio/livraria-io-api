package dev.db.livrariaio.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private int quantidadeDeLivros;

    private BigDecimal precoItem;

    public Item(Long id, Livro livro, int quantidadeDeLivros, BigDecimal precoItem) {
        this.id = id;
        this.livro = livro;
        this.quantidadeDeLivros = 1;
        this.precoItem = livro.getPreco().multiply(new BigDecimal(this.getQuantidadeDeLivros()));
    }
}