package br.com.bb.DTO.Materias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MateriasReceive {
    private String nome;

    private int horas;
}
