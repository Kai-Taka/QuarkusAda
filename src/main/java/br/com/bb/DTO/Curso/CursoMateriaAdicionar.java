package br.com.bb.DTO.Curso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CursoMateriaAdicionar {
    private String curso;

    private String materia;
}
