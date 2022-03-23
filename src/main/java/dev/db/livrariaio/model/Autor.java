package dev.db.livrariaio.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Builder
@EqualsAndHashCode
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    @Length(min = 3, max = 100, message = "O nome deverá ter no mínimo {min} e no máximo {max} caracteres.")
    private String nome;

    private String email;

    @Column(length = 1000)
    private String descricao;

    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;
}
