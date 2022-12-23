package br.com.bb.DTO.Materias;

import java.util.Set;

import br.com.bb.DTO.Curso.CursoSendSimple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MateriasSend {
    
    private Set<CursoSendSimple> cursos;

    private String nome;

    private int horas;

}
