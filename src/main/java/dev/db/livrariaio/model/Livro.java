package dev.db.livrariaio.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    @NotBlank(message = "O título é obrigatório!")
    @Length(min = 3, max = 500, message = "O título deverá ter no mínimo {min} e no máximo {max} caracteres.")
    private String titulo;

    @Column(length = 5000)
    private String sumario;

    private BigDecimal preco;

    private Integer numeroPaginas;

    private String isbn;

    private LocalDate dataPublicacao;

    private String capa;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Autor autor;
}
