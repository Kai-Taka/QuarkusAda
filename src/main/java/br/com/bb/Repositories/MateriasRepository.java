package br.com.bb.Repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.bb.model.Materias;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class MateriasRepository implements PanacheRepositoryBase<Materias, Integer>{

    public List<Materias> getAll()
    {
        return listAll();
    }
    
}
