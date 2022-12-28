package br.com.bb.DTO.Curso;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CursoReceive {
    
    @NotBlank(message = "Curso deve ter nome")
    String nome;
   
    @Positive(message = "Curso deve ter um periódo minimo positivo")
    @NotBlank(message = "Curso deve ter quantidade minima de anos para ser completado")
    int anos;

}
