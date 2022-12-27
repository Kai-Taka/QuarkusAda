package br.com.bb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.bb.DTO.Curso.CursoSendSimple;
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
    (
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JoinTable
    (
        name = "cursos_pertence",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "materias_id")
    )
    @JsonIgnore
    List<Materias> gradeDeMaterias;

    @OneToMany(mappedBy = "cursa")
    @JsonIgnore
    List<Aluno> alunosMatriculados;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    (
        name = "titular"
    )
    Professor titular;

    public CursoSendSimple toCursoSendSimple() {
        return CursoSendSimple.builder()
                .anos(this.anos)
                .nome(this.nome)
                .build();
    }


}
