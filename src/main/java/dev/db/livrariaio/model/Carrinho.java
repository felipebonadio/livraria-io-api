package dev.db.livrariaio.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;


@Builder
@EqualsAndHashCode
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Item> itens;

    public void adicionarItem(Item item){
        this.itens.add(item);
    }

    public void removerItem(Item item){
        this.itens.remove(item);
    }

    public BigDecimal somarPrecoTotal(){
        return itens.stream().map(Item::getPrecoItem).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
