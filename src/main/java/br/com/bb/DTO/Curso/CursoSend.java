package br.com.bb.DTO.Curso;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bb.DTO.Materias.MateriaSendSimple;
import br.com.bb.model.Curso;
import lombok.Data;

@Data

public class CursoSend {
    private String nome;
    private int anos;
    private List<MateriaSendSimple> grade;

    public CursoSend(Curso curso)
    {
        this.nome = curso.getNome();
        this.anos = curso.getAnos();

        this.grade = curso.getGradeDeMaterias()
                    .stream()
                    .map(m -> new MateriaSendSimple(m.getNome(), m.getHoras()))
                    .collect(Collectors.toList());
    }
}
