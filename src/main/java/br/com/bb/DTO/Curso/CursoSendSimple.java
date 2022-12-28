package br.com.bb.DTO.Curso;

import br.com.bb.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CursoSendSimple {
    
    private String nome;

    private int anos;

    public CursoSendSimple(Curso curso)
    {
        this.nome = curso.getNome();
        this.anos = curso.getAnos();
    }

}
