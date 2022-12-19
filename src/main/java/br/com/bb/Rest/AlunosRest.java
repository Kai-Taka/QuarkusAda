package br.com.bb.Rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import br.com.bb.DTO.AlunoDto;
import org.slf4j.LoggerFactory;

@Path("/alunos")
public class AlunosRest {
    private static Map<Integer, AlunoDto> db = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(AlunosRest.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAluno(AlunoDto aluno)
    {
        log.info("Attempting to add " + aluno);

        if(db.get(aluno.getId()) == null)
        {
            db.put(aluno.getId(), aluno);
            return Response.created(null).build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("'Já exite um aluno com este ID'")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @GET
    public Response getAllAlunos(@QueryParam("prefixo") String prefix)
    {
        log.info("Getting all alunos with prefix: " + prefix);

        AlunoDto[] filtered = (AlunoDto[]) db.values().stream()
        .filter(a -> a.getName().startsWith(prefix))
        .toArray();

        if (filtered.length == 0)
        {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(prefix == null ? 
                    "Não há nenhum aluno cadastrado" : 
                    "Não há alunos que comecem com " + prefix)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(Arrays.toString(filtered))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Path("/{id}")
    @GET
    public Response getAlunoEspecifico
    (
        @PathParam("id") Integer id
    )
    {
        if (db.containsKey(id))
        {
            return Response
                    .status(Response.Status.OK)
                    .entity(db.get(id))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("Não há aluno com este id")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateAluno(String name, @PathParam("id") Integer id)
    {
        if (db.get(id) == null)
        {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Não há aluno com esse id")
                    .build();
        }
        db.get(id).setName(name);

        return Response
                .status(Response.Status.OK)
                .entity(db.get(id))
                .build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteAlunoEspecifico(@PathParam("id") Integer id)
    {
        if (db.containsKey(id))
        {
            db.remove(id);
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("Removido aluno com id: " + id)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity("Não há aluno com este id")
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}