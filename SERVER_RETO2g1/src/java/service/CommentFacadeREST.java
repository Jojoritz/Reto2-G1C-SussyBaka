/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.CommentEJB;
import ejb.interfaces.AbstractEJB;
import entities.Comment;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author yeguo
 */
@Path("comment")
public class CommentFacadeREST {

    private static final Logger LOG
            = Logger.getLogger(CommentFacadeREST.class.getName());

    @EJB
    private CommentEJB ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Comment entity) {
        try {
            LOG.log(Level.INFO, "CommentRESTful service POST: create {0}",
                    entity.getClass().getName());
            ejb.create(entity);
        } catch (CreateException e) {
            LOG.log(Level.SEVERE,
                    "CommentRESTful service POST: Exception on create {0}",
                    e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Comment entity) {
        try {
            LOG.log(Level.INFO, "CommentRESTful service PUT: edit {0}",
                    entity.getClass().getName());
            ejb.edit(entity);
        } catch (UpdateException e) {
            LOG.log(Level.SEVERE,
                    "CommentRESTful service PUT: Exception on edit {0}",
                    e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOG.log(Level.INFO,
                    "CommentRESTful service DELETE: deleting comment with id: {0}", id);
            ejb.remove(ejb.find(id));
        } catch (ReadException | DeleteException e) {
            LOG.log(Level.SEVERE,
                    "CommentRESTful service DELETE: Exception on delete {0}",
                    e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Comment find(@PathParam("id") Integer id) {
        try {
            Comment comment;
            LOG.log(Level.INFO,
                    "CommentRESTful service GET: get comment with id: {0}", id);
            comment = ejb.find(id);
            return comment;
        } catch (ReadException e) {
            LOG.log(Level.SEVERE,
                    "CommentRESTful service GET: Exception on get {0}",
                    e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("post/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Comment> findAll(@PathParam("id") Integer id) {
        try {
            List<Comment> comments;
            LOG.log(Level.INFO,
                    "CommentRESTful service GET: get all comments from post with id: {0}", id);
            comments = ejb.getComments(id);
            return comments;
        } catch (ReadException e) {
            LOG.log(Level.SEVERE,
                    "CommentRESTful service GET: Exception on get {0}",
                    e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}
