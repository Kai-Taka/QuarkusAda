package br.com.bb.Repositories;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import br.com.bb.Errors.NotInDatabaseException;
import br.com.bb.model.Professor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ProfessorRepository implements PanacheRepositoryBase<Professor, Integer>{
    
    public List<Professor> getAll()
    {
        return listAll();
    }

    public Professor getById(int id)
    {
        Professor professor = findById(id);

        if (professor == null)
        {
            throw new NotInDatabaseException("Professor de id: " +
                                            id + " não existe");
        }

        return professor;
    }

}
