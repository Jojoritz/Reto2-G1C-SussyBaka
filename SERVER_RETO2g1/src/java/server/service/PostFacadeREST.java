/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import server.entities.Post;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.ReadException;
import server.exception.UpdateException;
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
import server.ejb.interfaces.PostEJBLocal;

/**
 *
 * @author ioritz
 */
@Path("entities.post")
public class PostFacadeREST {

    private static final Logger LOG = Logger.getLogger("PostFacadeREST");

    @EJB
    private PostEJBLocal ejb;

    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Post entity) {
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
            ejb.remove(id);
        } catch (DeleteException e) {
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
    @Path("{courseId}/date/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> findByDate(@PathParam("courseId") Integer courseId, @PathParam("date") String dateString) {
        try {
            List<Post> posts;
            LOG.log(Level.INFO,
                    "PostRESTful service GET: get post with id: {0}", dateString);
            Date date = FORMAT.parse(dateString);
            posts = ejb.getPostByDate(courseId, date);
            return posts;
        } catch (ReadException | ParseException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("{courseId}/date/{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> findByDateRange(@PathParam("courseId") Integer courseId, @PathParam("from") String dateFromString, @PathParam("to") String dateToString) {
        try {
            List<Post> posts;
            LOG.log(Level.INFO, String.format("PostRESTful service GET: get posts from post with between %s and %s from courseId %d",
                    dateFromString, dateToString, courseId));
            Date dateFrom = FORMAT.parse(dateFromString);
            Date dateTo = FORMAT.parse(dateToString);
            posts = ejb.getPostByDateRange(courseId, dateFrom, dateTo);
            return posts;
        } catch (ReadException | ParseException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @GET
    @Path("{courseId}/title/{title}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> findByTitle(@PathParam("courseId") Integer courseId, @PathParam("title") String title) {
        try {
            List<Post> posts;
            LOG.log(Level.INFO, String.format("PostRESTful service GET: get posts with title %s from course %s",
                    title, courseId));
            posts = ejb.getPostByTitle(courseId, title);
            return posts;
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @GET
    @Path("post/{courseId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> getCoursePosts(@PathParam("courseId") Integer courseId) {
        try {
            List<Post> posts;
            LOG.log(Level.INFO, String.format("PostRESTful service GET: get posts from course %s", courseId));
            posts = ejb.getCoursePosts(courseId);
            return posts;
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

    }

}
