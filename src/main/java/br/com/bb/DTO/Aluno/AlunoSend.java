package br.com.bb.DTO.Aluno;

import br.com.bb.DTO.Curso.CursoSend;
import br.com.bb.DTO.Professor.ProfessorSendSimple;
import br.com.bb.model.Aluno;
import lombok.Data;

@Data
public class AlunoSend {
    
    String nome;
    CursoSend curso;
    ProfessorSendSimple tutor;

    public AlunoSend(Aluno aluno)
    {
        this.nome = aluno.getNome();
        this.curso = new CursoSend(aluno.getCursa());
        if (aluno.getTutor() != null)
        {
            this.tutor = new ProfessorSendSimple(aluno.getTutor());
        }
        
    }

}
