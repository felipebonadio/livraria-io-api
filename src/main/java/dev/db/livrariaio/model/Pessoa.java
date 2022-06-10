package dev.db.livrariaio.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @OneToOne
    private Carrinho carrinho;

    @OneToOne(cascade=CascadeType.PERSIST)
    private Endereco endereco;
}
