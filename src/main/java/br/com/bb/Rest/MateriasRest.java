package br.com.bb.Rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.bb.DTO.Materias.MateriasReceive;
import br.com.bb.service.MateriasService;
import br.com.bb.utils.StandardResponse;

@Path("/materias")
public class MateriasRest {
    
    @Inject
    MateriasService service;

    @GET
    public Response getAll()
    {
        return StandardResponse.get(service.getAll(),
             "não há materias cadastradas no sistema");
    }

    @POST
    public Response create(MateriasReceive materias)
    {
        return StandardResponse.create(service.createMaterias(materias),
                                    "Erro na criação da materia, já existe");
    }

}   
