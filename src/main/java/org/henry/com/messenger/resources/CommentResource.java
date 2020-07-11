package org.henry.com.messenger.resources;


import org.henry.com.messenger.model.Comment;
import org.henry.com.messenger.service.CommentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId){
        return commentService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("commentId") long commentId, @PathParam("messageId") long messageId){
        return commentService.getComment(messageId, commentId);
    }

    @POST
    public Comment addMessage(@PathParam("messageId") long messageId, Comment comment){
        return commentService.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateMessage(
            @PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment
    ){
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }


    @DELETE
    @Path("/{commentId}")
    public Comment deleteMessage(
            @PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
        return commentService.removeComment(messageId, commentId);
    }


}
