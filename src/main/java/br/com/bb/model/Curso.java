package br.com.bb.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

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
public class Curso {
    @Id
    @GeneratedValue
    private int id;

    private String nome;

    private int anos;

    @ManyToMany
    @JoinTable
    (
        name = "cursos_pertence",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "materias_id")
    )
    Set<Materias> gradeDeMaterias;


    public CursoSend toCursoSend()
    {
        Set<MateriasSend> grade = new HashSet<>();

        this.gradeDeMaterias.stream()
        .map(m -> m.toMateriaSend())
        .forEach(m -> grade.add(m));

        return CursoSend.builder()
                .grade(grade)
                .anos(this.anos)
                .nome(this.nome).build();
    }


}
