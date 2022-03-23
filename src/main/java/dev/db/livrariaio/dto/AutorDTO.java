package dev.db.livrariaio.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AutorDTO {

    private Long id;

    private String nome;

    private String email;

    private String descricao;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dataCriacao;
}
