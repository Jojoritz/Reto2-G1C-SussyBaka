/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service;

import server.entities.Course;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import server.ejb.CourseEJB;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.ReadException;
import server.exception.UpdateException;

/**
 *
 * @author 2dam
 */
@Path("entities.course")
public class CourseFacadeREST {


    @EJB
    private CourseEJB ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Course entity) {
        try {
            ejb.create(entity);
        } catch (CreateException ex) {
            Logger.getLogger(CourseFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Course entity) {
        try {
            ejb.edit(entity);
        } catch (UpdateException ex) {
            Logger.getLogger(CourseFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ejb.remove(ejb.find(id));
        } catch (ReadException | DeleteException ex) {
            Logger.getLogger(CourseFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Course find(@PathParam("id") Integer id) {
        try {
            return ejb.find(id);
        } catch (ReadException ex) {
            Logger.getLogger(CourseFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Course> findAll() {
        try {
            return ejb.findAll();
        } catch (ReadException ex) {
            Logger.getLogger(CourseFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
