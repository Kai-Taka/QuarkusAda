package br.com.bb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.bb.DTO.Professor.ProfessorSend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
   
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String titulo;

    private char sexo;

    public ProfessorSend toSendProfessor()
    {
        return ProfessorSend.builder()
                .nome(this.name)
                .titulo(this.titulo)
                .sexo(this.sexo).build();
    }
}
