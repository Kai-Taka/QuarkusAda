package br.com.bb.DTO.Materias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class MateriaSendSimple {
    
    private String nome;

    private int horas;
}
