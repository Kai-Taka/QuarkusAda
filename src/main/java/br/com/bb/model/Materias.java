package br.com.bb.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import br.com.bb.DTO.Curso.CursoSend;
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

    @ManyToMany(mappedBy = "gradeDeMaterias")
    Set<Curso> cursos_pertence;

    public MateriasSend toMateriaSend()
    {
        Set<CursoSend> cursos = new HashSet<>();

        this.cursos_pertence.stream()
        .map(c -> c.toCursoSend())
        .forEach(c -> cursos.add(c));;

        return MateriasSend.builder()
                .cursos(cursos)
                .horas(this.horas)
                .nome(this.nome).build();
    }
}
