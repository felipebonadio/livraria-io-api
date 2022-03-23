package dev.db.livrariaio.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "O nome é obrigatório!")
    @Length(min = 3, max = 100, message = "O nome deverá ter no mínimo {min} e no máximo {max} caracteres.")
    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Livro> livros;
}
