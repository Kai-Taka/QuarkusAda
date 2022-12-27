package br.com.bb.DTO.Professor;

import br.com.bb.model.Professor;
import lombok.Data;

@Data
public class ProfessorSendSimple {
    
    private String nome;

    private String titulo;

    private Character sexo;
    
    public ProfessorSendSimple(Professor tutor) 
    {
        this.nome = tutor.getName();
        this.titulo = tutor.getTitulo();
        this.sexo = tutor.getSexo();
    }

}
