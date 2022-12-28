package br.com.bb.DTO.Professor;

import java.util.List;

import br.com.bb.model.Aluno;
import br.com.bb.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProfessorSend {

    private String nome;

    private String titulo;

    private List<Aluno> tutorados;
    
    public ProfessorSend(Professor tutor) 
    {
        this.nome = tutor.getName();
        this.titulo = tutor.getTitulo();
        this.tutorados = tutor.getTutorados();
    }

}
