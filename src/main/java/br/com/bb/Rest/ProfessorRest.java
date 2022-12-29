package br.com.bb.Rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.bb.DTO.ErroResponse;
import br.com.bb.DTO.Professor.ProfessorReceive;
import br.com.bb.service.ProfessorService;
import br.com.bb.utils.StandardResponse;

@Path("/professores")
public class ProfessorRest {

    ProfessorService service;

    @Inject
    ProfessorRest(ProfessorService service)
    {
        this.service = service;
    }

    @GET
    public Response getProfessores()
    {
        return StandardResponse.get(service.getProfessores(),
                        "Não há professores cadastrados");
    }

    @Path("/{id}")
    @GET
    public Response getProfessorId(@PathParam("id") int id)
    {
        return StandardResponse.get(Optional.of(service.getProfessor(id)),
                                "Não existe professor com id " +
                                id + " cadastrado no sistema");
    }

    @Path("/{id}/curso")
    @GET
    public Response getCursoProfessor(@PathParam("id") Integer id)
    {
        return StandardResponse.ok(service.getCurso(id));
    }

    @POST
    @Transactional
    public Response createProfessor(ProfessorReceive prof)
    {
        return StandardResponse.create(service.createProfessor(prof),
                            "Standard error"    );
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response putProfessorId(@PathParam("id") int id, ProfessorReceive professor)
    {
        return StandardResponse.update(service.updateProfessor(id, professor), 
                                    "Professor de id "+ id + " não existe");
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response delProfessorId(@PathParam("id") int id)
    {
        service.delProfessor(id);
        return StandardResponse.noContent();                           
    }

    @Path("/{idProfessor}/curso/{idCurso}")
    @PATCH
    @Transactional
    public Response linkCursoProfessor(@PathParam("idProfessor")Integer idProfessor, @PathParam("idCurso") Integer idCurso)
    {
        return StandardResponse.ok(service.linkCursoProfessor(idProfessor, idCurso));
    }

}
