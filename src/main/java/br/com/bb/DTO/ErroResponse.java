package br.com.bb.DTO;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErroResponse {
    
    private String message;

    private List<ErroMessage> errors;

    public ErroResponse(ConstraintViolationException e)
    {
        this.errors = e.getConstraintViolations().stream()
        .map
        (
            ev -> new ErroMessage(ev.getPropertyPath().toString(), ev.getMessage())
        )
        .collect(Collectors.toList());

        this.message = "Erros";
    }

}
