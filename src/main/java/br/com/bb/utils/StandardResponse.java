package br.com.bb.utils;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.bb.DTO.ErroResponse;
import br.com.bb.DTO.MessageDto;

public final class StandardResponse {
    
    public static Response ok(Optional opt)
    {
        return Response
                .ok(opt.get())
                .build();
    }

    public static Response notFound(String err)
    {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(MessageDto.builder()
                        .message(err)
                        .build()
                    )
                .build();
    }

    public static Response noContent()
    {
        return Response
                .noContent()
                .build();
    }

    public static Response badRequest(String err)
    {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(MessageDto.builder()
                        .message(err)
                        .build())
                .build();
    }

    public static Response created(Optional opt)
    {
        return Response
                .status(Response.Status.CREATED)
                .entity(opt.get())
                .build();
    }

    public static final Response get(Optional opt, String err)
    {
        if (opt.isPresent())
        {
            return ok(opt);
        }
        
        return notFound(err);
    }

    public static Response get(List list, String err) {
        if (list.isEmpty())
        {
            return notFound(err);
        }
        return ok(Optional.of(list));
    }

    public static Response create(Optional opt, String err)
    {
        if (opt.isPresent())
        {
            return created(opt);
        }
        return badRequest(err);
    }

    public static Response update(Optional opt, String err)
    {
        if (opt.isPresent())
        {
            return ok(opt);
        }
        return badRequest(err);
    }

    public static Response badRequest(ErroResponse erroResponse) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(erroResponse)
                .build();
    }

}
