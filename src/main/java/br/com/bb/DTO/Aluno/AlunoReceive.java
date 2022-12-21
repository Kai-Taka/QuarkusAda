package br.com.bb.DTO.Aluno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AlunoReceive {
    String name;
    Integer matricula;
    String curso;
}
