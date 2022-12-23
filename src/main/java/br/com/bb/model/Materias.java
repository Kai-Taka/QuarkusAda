package br.com.bb.model;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.com.bb.DTO.Curso.CursoSendSimple;
import br.com.bb.DTO.Materias.MateriaSendSimple;
import br.com.bb.DTO.Materias.MateriasSend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Materias {
    
    @Id
    @GeneratedValue
    int id;

    String nome;

    int horas;

    @ManyToMany
    (
        mappedBy = "gradeDeMaterias",
        fetch = FetchType.LAZY
    )
    
    List<Curso> cursos_pertence;

    public MateriasSend toMateriaSend()
    {
        Set<CursoSendSimple> cursos = new HashSet<>();

        this.cursos_pertence.stream()
        .map(c -> c.toCursoSendSimple())
        .forEach(c -> cursos.add(c));;

        return MateriasSend.builder()
                .cursos(cursos)
                .horas(this.horas)
                .nome(this.nome).build();
    }

    public MateriaSendSimple toMateriaSendSimple()
    {
        return new MateriaSendSimple(this.nome, this.horas);
    }


}
