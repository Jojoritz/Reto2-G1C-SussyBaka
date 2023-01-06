/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.PostEJB;
import entities.Course;
import entities.Post;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.Date;
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
@Path("post")
public class PostFacadeREST {

    private static final Logger LOG = Logger.getLogger("PostFacadeREST");

    @EJB
    private PostEJB ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Post entity) {
        try {
            LOG.log(Level.INFO, "PostRESTful service POST: create {0}",
                    entity.getClass().getName());
            ejb.create(entity);
        } catch (CreateException e) {
            LOG.log(Level.SEVERE,
                    "PostRESTful service POST: Exception on create {0}",
                    e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Post entity) {
        try {
            LOG.log(Level.INFO, "PostRESTful service PUT: edit {0}",
                    entity.getClass().getName());
            ejb.edit(entity);
        } catch (UpdateException e) {
            LOG.log(Level.SEVERE,
                    "PostRESTful service PUT: Exception on edit {0}",
                    e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOG.log(Level.INFO,
                    "PostRESTful service DELETE: deleting post with id: {0}", id);
            ejb.remove(ejb.find(id));
        } catch (ReadException | DeleteException e) {
            LOG.log(Level.SEVERE,
                    "PostRESTful service DELETE: Exception on delete {0}",
                    e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Post find(@PathParam("id") Integer id) {
        try {
            Post post;

            LOG.log(Level.INFO,
                    "PostRESTful service GET: get post with id: {0}", id);
            post = ejb.find(id);
            return post;
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("{course}/date/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> findByDate(@PathParam("course") Course course, @PathParam("id") Date date) {
        try {
            List<Post> posts;
            LOG.log(Level.INFO,
                    "PostRESTful service GET: get post with id: {0}", date);
            posts = ejb.getPostByDate(course, date);
            return posts;
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("{course}/date/{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> findByDateRange(@PathParam("course") Course course, @PathParam("from") Date from, @PathParam("to") Date to) {
        try {
            List<Post> posts;
            LOG.log(Level.INFO, String.format("PostRESTful service GET: get posts from post with between %s and %s from course %s",
                    from, to, course.getName()));
            posts = ejb.getPostByDateRange(course, from, to);
            return posts;
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @GET
    @Path("{course}/title/{title}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> findByTitle(@PathParam("course") Course course, @PathParam("title") String title) {
        try {
            List<Post> posts;
            LOG.log(Level.INFO, String.format("PostRESTful service GET: get posts with title %s from course %s",
                    title, course.getName()));
            posts = ejb.getPostByTitle(course, title);
            return posts;
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }

}
