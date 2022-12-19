package br.com.bb.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.bb.DTO.ProfessorDto;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class ProfessorService {
    
    public List<ProfessorDto> getProfessores()
    {
        log.info("Retornando todos os professores");
        return new ArrayList<ProfessorDto>();
    }

    public ProfessorDto getProfessor(int id) {
        log.info("Retornando professor de id " + id);
        return new ProfessorDto();
    }

    public ProfessorDto updateProfessor(int id) {
        log.info("Atualizando profssor: " + id);
        return new ProfessorDto();
    }

    public void delProfessor(int id) {
        log.info("Deletamdp professor: " + id);
    }

    public ProfessorDto createProfessor() {
        log.info("Creating new professor  ");
        return new ProfessorDto();
    }

}
