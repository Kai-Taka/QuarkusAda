package br.com.bb.Repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;

import org.jboss.resteasy.util.NoContent;

import br.com.bb.Errors.NotInDatabaseException;
import br.com.bb.model.Curso;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CursosRepository implements PanacheRepositoryBase<Curso, Integer>{

    public List<Curso> getAll()
    {
        return listAll();
    }

    public Curso getCurso(String nome) {
        Curso curso = find("nome", nome).firstResult();

        if (curso == null)
        {
            throw new NotInDatabaseException(nome + " is not present in the DB");
        }

        return curso;
    }

    public Curso getCurso(Integer id) {
        Curso curso = findById(id);
        if (curso == null) throw new NotInDatabaseException("Curso com id: " + id + " não existe");

        return curso;
    }
    
}
