package br.com.bb.Rest;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
        return StandardResponse.get(service.getAll(), 
                    "Não existem cursos cadastrados");
    }

    @PUT
    @Transactional
    public Response linkMateria(CursoMateriaAdicionar toAdd)
    {
        return StandardResponse.update(service.adicionarMateria(toAdd),
                                        "Erro ao adicionar materia");
    }

}