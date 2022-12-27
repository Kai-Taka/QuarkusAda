package br.com.bb.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.bb.DTO.Aluno.AlunoReceive;
import br.com.bb.DTO.Aluno.AlunoSend;
import br.com.bb.DTO.Professor.ProfessorSend;
import br.com.bb.DTO.Professor.ProfessorSendSimple;
import br.com.bb.Errors.NotInDatabaseException;
import br.com.bb.Repositories.AlunoRepository;
import br.com.bb.Repositories.CursosRepository;
import br.com.bb.Repositories.ProfessorRepository;
import br.com.bb.model.Aluno;
import br.com.bb.model.Professor;

@ApplicationScoped
public class AlunoService {
    
    @Inject
    private AlunoRepository rep;

    @Inject
    private CursosRepository cursoRep;

    @Inject
    private ProfessorRepository profRep;

    public Optional<AlunoSend> createAluno(@Valid AlunoReceive alunoR)
    {
        Aluno aluno = Aluno.builder()
                        .nome(alunoR.getName())
                        .cursa(cursoRep.getCurso(alunoR.getCurso()))
                        .build();

        rep.persist(aluno);

        return Optional.of(new AlunoSend(aluno));
    }

    public Optional<List<AlunoSend>> getAll()
    {
        List<Aluno> alunos = rep.getAll();

        if (alunos.size() <= 0)
        {
            throw new NotInDatabaseException("Alunos is empty");
        }

        return Optional.of(alunos.stream()
                .map(a -> new AlunoSend(a))
                .collect(Collectors.toList()));
    }

    public Optional<AlunoSend> adicionarTutor(Integer idAluno, Integer idProfessor) {
        
        Aluno aluno = rep.findById(idAluno);

        Professor professor = profRep.findById(idProfessor);

        if (aluno == null) throw new NotInDatabaseException("Aluno não existe");
        if (professor == null) throw new NotInDatabaseException("Professor não existe");

        aluno.setTutor(professor);
        rep.persist(aluno);

        return Optional.of(new AlunoSend(aluno));
    }

    public Optional<ProfessorSendSimple> getTutor(Integer id) {

        Professor tutor = rep.getById(id).getTutor();
        if (tutor == null) throw new NotInDatabaseException("Aluno não tem tutor");

        return Optional.of(new ProfessorSendSimple(tutor));
    }

    public Optional<AlunoSend> getAluno(Integer id)
    {
        return Optional.of(new AlunoSend(rep.getById(id)));
    }

    public Optional<AlunoSend> updateAlunoName(String name, Integer id) {
        Aluno aluno = rep.getById(id);

        aluno.setNome(name);
        
        rep.persistAndFlush(aluno);

        return Optional.of(new AlunoSend(aluno));
    }

    public void deleteAlunoEspecifico(Integer id) {
        rep.deleteById(id);

    }

}
