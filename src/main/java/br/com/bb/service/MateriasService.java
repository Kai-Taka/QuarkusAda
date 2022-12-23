package br.com.bb.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bb.DTO.Materias.MateriasReceive;
import br.com.bb.DTO.Materias.MateriasSend;
import br.com.bb.dao.MateriasRepository;
import br.com.bb.model.Curso;
import br.com.bb.model.Materias;

@ApplicationScoped
public class MateriasService {

    @Inject
    MateriasRepository rep;

    public List<MateriasSend> getAll()
    {
        List<MateriasSend> materias = new LinkedList<>();

        rep.getAll().stream()
        .map(m -> m.toMateriaSend())
        .forEach(m -> materias.add(m));

        return materias;

    }

    public Optional<MateriasSend> createMaterias(MateriasReceive materiasR)
    {
        Materias materias = Materias.builder()
                                .horas(materiasR.getHoras())
                                .nome(materiasR.getNome())
                                .cursos_pertence(new LinkedList<Curso>())
                                .build();

        rep.persistAndFlush(materias);

        return Optional.of(materias.toMateriaSend());
    }
    
}
