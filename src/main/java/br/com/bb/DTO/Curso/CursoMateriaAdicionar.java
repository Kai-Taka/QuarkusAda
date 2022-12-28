package br.com.bb.DTO.Curso;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CursoMateriaAdicionar {

    @NotBlank(message = "Deve ser nomeado algum curso para inserir a materia")
    private String curso;
    
    @NotBlank(message = "Deve ser informado a materia a ser adicionada ao curso")
    private String materia;
}
