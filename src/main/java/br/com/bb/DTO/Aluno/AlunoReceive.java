package br.com.bb.DTO.Aluno;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlunoReceive {
    @NotBlank
    private String name;
    @NotBlank
    private String curso;
}
