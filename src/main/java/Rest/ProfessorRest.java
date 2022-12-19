package Rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/professores")
public class ProfessorRest {
    
    private static final Logger log = LoggerFactory.getLogger(ProfessorRest.class);

    @GET
    public void getProfessores()
    {
        log.info("Mostrando todos professores");
    }

    @Path("/{id}")
    @GET
    public void getProfessorId(@PathParam("id") int id)
    {
        log.info("mostrando professor " + id);
    }

    @Path("/{id}")
    @PUT
    public void putProfessorId(@PathParam("id") int id)
    {
        log.info("atualizando professor " + id);
    }

    @Path("/{id}")
    @DELETE
    public void delProfessorId(@PathParam("id") int id)
    {
        log.info("deleting professor " + id);
    }
}
