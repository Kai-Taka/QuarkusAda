package br.com.bb.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroMessage {
    private String location;

    private String reason;
}
