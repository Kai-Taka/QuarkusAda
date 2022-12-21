package br.com.bb.DTO.Professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProfessorReceive {

    private String nome;

    private String titulo;

    private Character sexo;
    
}