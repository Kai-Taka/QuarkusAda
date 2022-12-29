package br.com.bb.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.action.internal.OrphanRemovalAction;

import br.com.bb.DTO.Curso.CursoSend;
import br.com.bb.DTO.Professor.ProfessorReceive;
import br.com.bb.DTO.Professor.ProfessorSend;
import br.com.bb.Errors.NotInDatabaseException;
import br.com.bb.Repositories.CursosRepository;
import br.com.bb.Repositories.ProfessorRepository;
import br.com.bb.model.Curso;
import br.com.bb.model.Professor;
import br.com.bb.utils.AutoSetGet;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class ProfessorService {

    @Inject
    ProfessorRepository rep;

    @Inject
    CursosRepository cursoRep;
    
    public List<ProfessorSend> getProfessores()
    {
        log.info("Retornando todos os professores");
        return rep.getAll().stream()
                    .map(p -> new ProfessorSend(p))
                    .collect(Collectors.toList());
    }

    public Optional<ProfessorSend> getProfessor(int id) {
        log.info("Retornando professor de id " + id);
        return Optional.of(new ProfessorSend(rep.getById(id)));
    }

    public Optional<ProfessorSend> updateProfessor(int id, @Valid ProfessorReceive prof) {
        log.info("Atualizando profssor: " + id);
        Professor professor = rep.getById(id);
        professor.setName(prof.getNome());
        professor.setTitulo(prof.getTitulo());
        
        rep.persist(professor);

        return Optional.of(new ProfessorSend(professor));

    }

    public void delProfessor(int id) {
        log.info("Deletando professor: " + id);

        rep.deleteById(id);
    }

    public Optional<ProfessorSend> createProfessor(@Valid ProfessorReceive prof) {
        log.info("Creating new professor  ");

        String.valueOf(AutoSetGet.allNonNull(prof));
        if (AutoSetGet.allNonNull(prof))
        {
            Professor professor = Professor.builder()
                                    .name(prof.getNome())
                                    .titulo(prof.getTitulo())
                                    .build();

            rep.persist(professor);

            return Optional.of(new ProfessorSend(professor));
        }

        return Optional.empty();
    }

    public Optional<CursoSend> getCurso(Integer id) {
        Professor professor = rep.getById(id);

        Curso curso = professor.getCursosMinistrado();
        if (curso == null) throw new NotInDatabaseException("Professo não ministra curso especifico");
        
        return Optional.of(new CursoSend(curso));
    }

    public Optional<ProfessorSend> linkCursoProfessor(Integer idProfessor, Integer idCurso) {
        
        Professor professor = rep.getById(idProfessor);

        Curso curso = cursoRep.getCurso(idCurso);

        professor.setCursosMinistrado(curso);
        rep.persist(professor);

        return Optional.of(new ProfessorSend(professor));

    }

}
