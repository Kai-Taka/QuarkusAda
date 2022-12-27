package br.com.bb.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries
(
    @NamedQuery(name = "Aluno.beginsWithName", query = "FROM Aluno WHERE nome LIKE ?1")
)

public class Aluno {
    
    @Id
    @GeneratedValue
    private int id;
    
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    (
        name = "cursa",
        foreignKey = @ForeignKey(name = "curso_id_fk")
    )
    Curso cursa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    (
        name = "tutor",
        foreignKey =  @ForeignKey(name = "professor_id_fk")
    )
    Professor tutor;




}
