package br.com.bb.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;

import br.com.bb.model.Curso;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class CursosRepository implements PanacheRepositoryBase<Curso, Integer>{

    public List<Curso> getAll()
    {
        return listAll();
    }
    
}
