package br.com.bb.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bb.DTO.Curso.CursoReceive;
import br.com.bb.DTO.Curso.CursoSend;
import br.com.bb.dao.CursosRepository;
import br.com.bb.model.Curso;
import br.com.bb.model.Materias;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CursosService {

    @Inject
    CursosRepository rep;

    public List<CursoSend> getAll()
    {
        List<CursoSend> cursos = new LinkedList<>();
        rep.getAll().stream()
            .map(c-> c.toCursoSend())
            .forEach(c -> cursos.add(c));

        return cursos;
        
    }

    public Optional<CursoSend> criarCurso(CursoReceive cursoR)
    {
        Curso curso = Curso.builder()
                        .anos(cursoR.getAnos())
                        .nome(cursoR.getNome())
                        .gradeDeMaterias(new HashSet<Materias>())
                        .build();

        rep.persistAndFlush(curso);

        return Optional.of(curso.toCursoSend());
    }

}
