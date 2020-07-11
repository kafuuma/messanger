package org.henry.com.messenger.exceptions;

import org.henry.com.messenger.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {


    @Override
    public Response toResponse(DataNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "http://messeger.doc");
        return Response.status(Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}
