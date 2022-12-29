package br.com.bb.Rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.bb.DTO.Curso.CursoMateriaAdicionar;
import br.com.bb.DTO.Curso.CursoReceive;
import br.com.bb.service.CursosService;
import br.com.bb.utils.StandardResponse;

@Path("/cursos")
public class CursosRest {

    @Inject
    CursosService service;
    
    @POST
    @Transactional
    public Response createCurso(CursoReceive curso)
    {
        return StandardResponse.created(
            service.criarCurso(curso)
        );
    }

    @GET
    @Transactional
    public Response getAllCursos()
    {
        return StandardResponse.ok(service.getAll());
    }

    @PUT
    @Transactional
    public Response linkMateria(CursoMateriaAdicionar toAdd)
    {
        return StandardResponse.update(service.adicionarMateria(toAdd),
                                        "Erro ao adicionar materia");
    }

    @Path("/{id}")
    @GET
    public Response getCursoEspecifico(@PathParam("id") Integer id)
    {
        return StandardResponse.ok(service.getCurso(id));
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deleteById(@PathParam("id") Integer id)
    {
        service.deleteById(id);
        
        return StandardResponse.noContent();
    }
}
