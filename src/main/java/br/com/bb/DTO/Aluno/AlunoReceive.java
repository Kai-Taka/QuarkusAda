package br.com.bb.DTO.Aluno;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlunoReceive {
    @NotBlank(message = "Name cannot be null")
    private String name;
    @NotBlank(message = "Curso cannot be null")
    private String curso;
}
