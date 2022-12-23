package br.com.bb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
        cascade = CascadeType.ALL
    )
    @JoinTable
    (
        name = "cursos_pertence",
        joinColumns = @JoinColumn(name = "curso_id"),
        inverseJoinColumns = @JoinColumn(name = "materias_id")
    )
    List<Materias> gradeDeMaterias;

    public CursoSendSimple toCursoSendSimple() {
        return CursoSendSimple.builder()
                .anos(this.anos)
                .nome(this.nome)
                .build();
    }


}
