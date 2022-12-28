package br.com.bb.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import br.com.bb.DTO.Materias.MateriasReceive;
import br.com.bb.DTO.Materias.MateriasSend;
import br.com.bb.Repositories.MateriasRepository;
import br.com.bb.model.Curso;
import br.com.bb.model.Materias;

@ApplicationScoped
public class MateriasService {

    @Inject
    MateriasRepository rep;

    public List<MateriasSend> getAll()
    {
        return rep.getAll().stream()
        .map(m -> new MateriasSend(m))
        .collect(Collectors.toList());

    }

    public Optional<MateriasSend> createMaterias(@Valid MateriasReceive materiasR)
    {
        Materias materias = Materias.builder()
                                .horas(materiasR.getHoras())
                                .nome(materiasR.getNome())
                                .cursos_pertence(new LinkedList<Curso>())
                                .build();

        rep.persistAndFlush(materias);

        return Optional.of(new MateriasSend(materias));
    }

    public void delete(Integer id) {
        rep.deleteById(id);
    }
    
}
