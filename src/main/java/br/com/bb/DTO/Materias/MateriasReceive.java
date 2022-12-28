package br.com.bb.DTO.Materias;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MateriasReceive {
    
    @NotBlank(message = "Materia deve ter nome")
    private String nome;

    @NotBlank(message = "Materia deve ter carga horaria")
    @Positive(message = "Carga horaria deve ser > 0")
    private int horas;
}
