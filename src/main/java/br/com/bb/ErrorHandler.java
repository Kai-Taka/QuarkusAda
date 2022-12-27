package br.com.bb;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.bb.DTO.ErroResponse;
import br.com.bb.Errors.NotInDatabaseException;
import br.com.bb.utils.StandardResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ErrorHandler implements ExceptionMapper<Exception>{@Override
    public Response toResponse(Exception e) {

        if (e instanceof NotInDatabaseException)
        {
            return StandardResponse.notFound(
                ErroResponse.builder()
                    .message(e.getMessage())
                    .build()   
            );
        }

        return StandardResponse.serverError(
            ErroResponse.builder()
                .message(e.getMessage())
                .build()
        );
    }
    
}