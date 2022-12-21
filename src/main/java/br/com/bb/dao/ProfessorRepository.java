package br.com.bb.dao;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import br.com.bb.model.Professor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Integer>{
    
    public List<Professor> getAll()
    {
        return listAll();
    }

    public Optional<Professor> getById(int id)
    {
        return findByIdOptional(id);
    }

}
