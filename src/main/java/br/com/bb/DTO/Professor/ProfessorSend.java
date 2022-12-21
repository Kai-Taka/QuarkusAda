package br.com.bb.DTO.Professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProfessorSend {
    
    private String nome;

    private String titulo;

    private Character sexo;
    
}
