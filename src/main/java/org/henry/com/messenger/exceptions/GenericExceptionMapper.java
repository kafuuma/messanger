package org.henry.com.messenger.exceptions;

import org.henry.com.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e ){
                ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 500, "http://messeger.doc");
                return Response.status(Status.INTERNAL_SERVER_ERROR)
                        .entity(errorMessage)
                        .build();
            }

}
