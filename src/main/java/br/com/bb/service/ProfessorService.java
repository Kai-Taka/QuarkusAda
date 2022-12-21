package br.com.bb.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bb.DTO.Professor.ProfessorReceive;
import br.com.bb.DTO.Professor.ProfessorSend;
import br.com.bb.dao.ProfessorRepository;
import br.com.bb.model.Professor;
import br.com.bb.utils.AutoSetGet;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class ProfessorService {

    @Inject
    ProfessorRepository rep;
    
    public List<ProfessorSend> getProfessores()
    {
        log.info("Retornando todos os professores");
        List<ProfessorSend> professores = new LinkedList<>();

        rep.findAll()
        .stream()
        .map(p -> p.toSendProfessor())
        .forEach(p -> professores.add(p));

        return professores; 
    }

    public Optional<Professor> getProfessor(int id) {
        log.info("Retornando professor de id " + id);
        return rep.getById(id);
    }

    public Optional<ProfessorSend> updateProfessor(int id, ProfessorReceive prof) {
        log.info("Atualizando profssor: " + id);
        Optional<Professor> opt = rep.getById(id);
        if (AutoSetGet.allNonNull(prof) && opt.isPresent())
        {
            Professor professor = opt.get();
            professor.setName(prof.getNome());
            professor.setSexo(prof.getSexo());
            professor.setTitulo(prof.getTitulo());
            
            rep.persist(professor);

            return Optional.of(professor.toSendProfessor());
            
        }

        return Optional.empty();
    }

    public void delProfessor(int id) {
        log.info("Deletando professor: " + id);

        rep.deleteById(id);
    }

    public Optional<ProfessorSend> createProfessor(ProfessorReceive prof) {
        log.info("Creating new professor  ");

        String.valueOf(AutoSetGet.allNonNull(prof));
        if (AutoSetGet.allNonNull(prof))
        {
            Professor professor = Professor.builder()
                                    .name(prof.getNome())
                                    .sexo(prof.getSexo())
                                    .titulo(prof.getTitulo())
                                    .build();

            rep.persist(professor);

            return Optional.of(professor.toSendProfessor());
        }

        return Optional.empty();
    }

}
