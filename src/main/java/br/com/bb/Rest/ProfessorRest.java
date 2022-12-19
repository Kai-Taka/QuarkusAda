package br.com.bb.Rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bb.service.ProfessorService;

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
       return Response
                .ok(service.getProfessores())
                .build();
    }

    @Path("/{id}")
    @GET
    public Response getProfessorId(@PathParam("id") int id)
    {
        return Response
                .ok(service.getProfessor(id))
                .build();
    }

    @Path("/{id}")
    @POST
    public Response createProfessor(@PathParam("id") int id)
    {
        return Response
                .ok(service.createProfessor())
                .build();
    }

    @Path("/{id}")
    @PUT
    public Response putProfessorId(@PathParam("id") int id)
    {
        return Response
                .ok(service.updateProfessor(id))
                .build();
    }

    @Path("/{id}")
    @DELETE
    public Response delProfessorId(@PathParam("id") int id)
    {
        service.delProfessor(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .type(MediaType.TEXT_PLAIN)
                .build();
}
}
