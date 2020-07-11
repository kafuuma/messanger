package org.henry.com.messenger.resources;


import org.henry.com.messenger.model.Message;
import org.henry.com.messenger.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService  messageService = new MessageService();
    @GET
    public List<Message> getMessages(
            @QueryParam("year") int year,
            @QueryParam("start") int start,
            @QueryParam("size") int size
            ){
        if(year > 0){
            return messageService.getAllMessagesForYear(year);
        }

        if(start >= 0 && size >= 0){
            return messageService.getAllMessagesPaginated(start, size);
        }
        return messageService.getAllMessages();
    }


    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo)  throws URISyntaxException {

        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
         URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
         return  Response.created(uri)
                .entity(newMessage)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message){
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public Message deleteMessage(@PathParam("messageId") long id){
        return messageService.removeMessage(id);
    }


    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo){
        Message message =  messageService.getMessage(id);

        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId", message.getId())
                .build();
        return uri.toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                         .path(ProfileResource.class)
                         .path(message.getAuthor())
                         .build();
        return uri.toString();
    }

    private String getUriForSelf(@Context UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                    .path(MessageResource.class)
                    .path(Long.toString(message.getId()))
                    .build()
                    .toString();
    }


    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }


}
