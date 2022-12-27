package br.com.bb.Rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.com.bb.DTO.Aluno.AlunoReceive;
import br.com.bb.service.AlunoService;
import br.com.bb.utils.StandardResponse;

@Path("/alunos")
public class AlunosRest {
    @Inject
    AlunoService service;

    @POST
    @Transactional
    public Response addAluno(AlunoReceive aluno)
    {
        return StandardResponse.created(service.createAluno(aluno));
    }

    @GET
    public Response getAllAlunos(@QueryParam("prefixo") String prefix)
    {
        return StandardResponse.ok(service.getAll()); 
    }

    @Path("/{id}")
    @GET
    public Response getAlunoEspecifico
    (
        @PathParam("id") Integer id
    )
    {
        
        return StandardResponse.ok(service.getAluno(id));
    }

    @Path("/{id}/tutor")
    @GET
    public Response getTutorDoAlunoEspecifico(@PathParam("id") Integer id)
    {
        return StandardResponse.ok(service.getTutor(id));
    }

    @Path("/{id}")
    @PUT
    public Response updateAluno(String name, @PathParam("id") Integer id)
    {
        return StandardResponse.ok(service.updateAlunoName(name, id));
    }

    @Path("/{id}")
    @DELETE
    public Response deleteAlunoEspecifico(@PathParam("id") Integer id)
    {
        service.deleteAlunoEspecifico(id);

        return StandardResponse.noContent();
    }
    
    @Path("{idAluno}/tutor/{idProfessor}")
    @PATCH
    @Transactional
    public Response anexarProfessorAoAluno(@PathParam("idAluno") Integer idAluno, @PathParam("idProfessor") Integer idProfessor)
    {
        return StandardResponse.ok(service.adicionarTutor(idAluno, idProfessor));
    }
}
