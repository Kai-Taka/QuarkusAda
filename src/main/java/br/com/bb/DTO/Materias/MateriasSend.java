package br.com.bb.DTO.Materias;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bb.DTO.Curso.CursoSendSimple;
import br.com.bb.model.Materias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MateriasSend {
    
    private List<CursoSendSimple> cursos;

    private String nome;

    private int horas;

    public MateriasSend(Materias materia)
    {
        this.nome = materia.getNome();
        this.horas = materia.getHoras();
        this.cursos = materia.getCursos_pertence()
                        .stream()
                        .map(c -> new CursoSendSimple(c))
                        .collect(Collectors.toList());
    }

}
