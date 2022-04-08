package dev.db.livrariaio.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import lombok.*;

@Builder
@EqualsAndHashCode
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public void removerItem(Long id) {
        Optional<Item> itemParaRemover = itens.stream().filter(map -> map.getId().equals(id)).findFirst();
        itemParaRemover.ifPresent(item -> this.itens.remove(item));
    }

    public BigDecimal somarPrecoTotal() {
        return itens.stream().map(Item::getPrecoItem).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Carrinho(Long id, List<Item> itens) {
        this.id = id;
        this.itens = itens;
    }

    public Carrinho(Long id) {
        this.id = id;
    }
}