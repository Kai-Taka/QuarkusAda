package br.com.bb.DTO.Professor;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProfessorReceive {

    @NotBlank(message = "Nome não pode ser nulo")
    private String nome;

    private String titulo;

    
    private Character sexo;
    
}
