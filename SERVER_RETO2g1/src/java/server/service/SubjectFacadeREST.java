/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service;

import java.util.List;
import server.ejb.interfaces.SubjectEJBLocal;
import server.entities.Subject;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.ReadException;
import server.exception.UpdateException;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ioritz
 */
@Path("entities.subject")
public class SubjectFacadeREST {

    /**
     * The EJB with the bussines logic of the subject
     */
    @EJB(beanName = "SubjectEJB")
    private SubjectEJBLocal ejb;

    /**
     * The logger of the class
     */
    private static final Logger LOGGER = Logger.getLogger(SubjectFacadeREST.class.getName());

    /**
     * The method to create the subject
     *
     * @param subject the data of the subject we want to create
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createSubject(Subject subject) {
        try {
            LOGGER.info("Creating the subject");
            ejb.create(subject);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * A method to modify the subject
     *
     * @param subject the data to modify
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modifySubject(Subject subject) {
        try {
            LOGGER.info("Mofifying the subject");
            ejb.edit(subject);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * A method to delete the subject
     *
     * @param subject the data of the subject to remove
     */
    @DELETE
    @Path("{subject}")
    public void removeSubject(@PathParam("subject") Subject subject) {
        try {
            LOGGER.info("Deleting the subject");
            ejb.remove(subject);
        } catch (DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * A method to find the subject
     *
     * @param subject the data to find the subject
     * @return the subject with the data
     */
    @GET
    @Path("subject/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Subject find(@PathParam("id") Integer id) {
        Subject subject = null;
        try {
            LOGGER.info("Searching the subject");
            subject = ejb.find(id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        return subject;
    }

    /**
     * A method to find a subject by the name
     *
     * @param name the data to find the subjectr
     * @return the subject with the data
     */
    @GET
    @Path("subject/name/{nameParam}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Subject searchByName(@PathParam("nameParam") String name) {
        Subject subject = null;
        try {
            LOGGER.info("Searching the subject by name");
            subject = ejb.searchByName(name);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        return subject;
    }

    /**
     * A method to find subjects by the type
     *
     * @param type the data to find the subjects
     * @return A set of subjects of this type
     */
    @GET
    @Path("subject/type/{type}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Subject> searchByType(@PathParam("type") String type) {
        List<Subject> subjects = null;
        try {
            LOGGER.info("Searching the subjects by the type");
            subjects = ejb.searchByType(type);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        return subjects;
    }

    /**
     * A method to find subjects by the level of them
     *
     * @param level the data to find the subjects
     * @return A set of subjects, with the subjects of the indicated level
     */
    @GET
    @Path("subject/level/{level}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Subject> searchByLevel(@PathParam("level") String level) {
        List<Subject> subjects = null;
        try {
            LOGGER.info("Searching the subjects by the level");
            subjects = ejb.searchByLevel(level);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        return subjects;
    }

    /**
     * A method to find the subject relationships data
     *
     * @param subject the subject data to search the relationships data
     * @return the subject with the relationships data
     */
    @GET
    @Path("subject/relationships/{subject}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Subject getSubjectRelationshipsData(@PathParam("subject") Subject subject) {
        try {
            LOGGER.info("Searching the data of the subject relationships");
            subject = ejb.getSubjectRelationshipsData(subject);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }

        return subject;
    }
    /**
     * The method to find the subjects
     * @return A set of subjects
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Subject> findAll() {
        List<Subject> subjects = null;
        try {
            LOGGER.info("Searching all the subjects");
            subjects = ejb.findAll();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        return subjects;
    }

}
