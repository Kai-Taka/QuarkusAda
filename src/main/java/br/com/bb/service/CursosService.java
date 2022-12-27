package br.com.bb.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bb.DTO.Curso.CursoMateriaAdicionar;
import br.com.bb.DTO.Curso.CursoReceive;
import br.com.bb.DTO.Curso.CursoSend;
import br.com.bb.Repositories.CursosRepository;
import br.com.bb.Repositories.MateriasRepository;
import br.com.bb.model.Curso;
import br.com.bb.model.Materias;
import io.vertx.core.cli.Option;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class CursosService {

    @Inject
    CursosRepository rep;

    @Inject
    MateriasRepository matRep;

    public List<CursoSend> getAll()
    {
        return rep.getAll().stream()
            .map(c-> new CursoSend(c))
            .collect(Collectors.toList());
        
    }

    public Optional<CursoSend> criarCurso(CursoReceive cursoR)
    {
        Curso curso = Curso.builder()
                        .anos(cursoR.getAnos())
                        .nome(cursoR.getNome())
                        .gradeDeMaterias(new LinkedList<Materias>())
                        .build();

        rep.persistAndFlush(curso);

        return Optional.of(new CursoSend(curso));
    }

    public Optional<CursoSend> adicionarMateria(CursoMateriaAdicionar toAdd)
    {
        Materias materia = matRep.find("nome", toAdd.getMateria()).firstResult();

        Curso curso = rep.find("nome", toAdd.getCurso()).firstResult();

        curso.getGradeDeMaterias().add(materia);

        //materia.getCursos_pertence().add(curso);

        return Optional.of(new CursoSend(curso));
    }

    public Optional<CursoSend> getCurso(Integer id) {
        return Optional.of(new CursoSend(rep.getCurso(id)));
    }

}
