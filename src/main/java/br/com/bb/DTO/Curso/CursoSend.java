package br.com.bb.DTO.Curso;

import java.util.Set;

import br.com.bb.DTO.Materias.MateriasSend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CursoSend {
    String nome;
    int anos;
    Set<MateriasSend> grade;
}
