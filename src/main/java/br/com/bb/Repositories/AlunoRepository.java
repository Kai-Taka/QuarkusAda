package br.com.bb.Repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.bb.Errors.NotInDatabaseException;
import br.com.bb.model.Aluno;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class AlunoRepository implements PanacheRepositoryBase<Aluno, Integer>{
    
    public List<Aluno> getAll()
    {
        return listAll();
    }

    public Aluno getById(int id)
    {
        Aluno aluno = findById(id);
        if (aluno == null) throw new NotInDatabaseException("Aluno de id " + id + " não existe");
        return aluno;
    }

}
