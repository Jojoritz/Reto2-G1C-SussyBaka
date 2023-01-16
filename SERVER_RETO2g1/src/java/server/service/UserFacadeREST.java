/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service;

import java.util.List;
import server.ejb.interfaces.UserEJBLocal;
import server.entities.User;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.ReadException;
import server.exception.UpdateException;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import server.entities.Student;
import server.entities.Teacher;

/**
 *
 * @author ioritz
 */
@Path("entities.user")
public class UserFacadeREST {

    /**
     * EJB object with the busines logic
     */
    @EJB
    private UserEJBLocal ejb;
    /**
     * The logger of this class
     */
    private static final Logger LOGGER = Logger.getLogger(UserFacadeREST.class.getName());

    /**
     * POST method to create users
     *
     * @param user the user with the data
     */
    @POST
    @Path("user")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createUser(User user) {
        try {
            LOGGER.info("Creating a user");
            ejb.create(user);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * POST method to create teacher
     *
     * @param user the user with the data
     */
    @POST
    @Path("user/teacher")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createTeacher(Teacher user) {
        try {
            LOGGER.info("Creating a Teacher");
            ejb.create(user);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * POST method to create users
     *
     * @param user the user with the data
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("user/student")
    public void createStudent(Student user) {
        try {
            LOGGER.info("Creating a Student");
            ejb.create(user);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * PUT method to update user
     *
     * @param user the data to update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modifyUser(User user) {
        try {
            LOGGER.info("Modifying a user");
            ejb.edit(user);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    
    /**
     * PUT method to update user
     *
     * @param user the data to update
     */
    @PUT
    @Path("user/student")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modifyStudent(Student user) {
        try {
            LOGGER.info("Modifying a user");
            ejb.edit(user);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    
    /**
     * PUT method to update user
     *
     * @param user the data to update
     */
    @PUT
    @Path("user/teacher")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modifyTeacher(Teacher user) {
        try {
            LOGGER.info("Modifying a user");
            ejb.edit(user);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * DELETE method to remove the user
     *
     * @param userId the data of user to remove
     */
    @DELETE
    @Path("user/{id}")
    public void removeUser(@PathParam("id") Integer userId) {
        try {
            LOGGER.info("Deleting the user");
            ejb.remove(userId);
        } catch (DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("user/{id}")
    public User getUser(@PathParam("id") Integer userId) {
        try {
            LOGGER.info("Find  the user");
            return ejb.find(userId);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * GET method to signIn a user
     *
     * @param xtUser the data of the user to signIn
     * @return the user finded
     */
    @GET
    @Path("user/login/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User signIn(@PathParam("login") String login, @PathParam("password") String password) {
        User user = null;
        try {
            LOGGER.info("Searching the user");
            user = ejb.signIn(login, password);

        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }

        return user;

    }

    /**
     */
    @GET
    @Path("user/teacher/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Teacher getTeacher(@PathParam("id") Integer id) {
        Teacher user = null;
        try {
            LOGGER.info("Searching the user");
            user = (Teacher) ejb.find(id);

        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }

        return user;

    }

    /**
     */
    @GET
    @Path("user/student/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student getStudent(@PathParam("id") Integer id) {
        Student user = null;
        try {
            LOGGER.info("Searching the user");
            user = (Student) ejb.find(id);

        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }

        return user;

    }

    /**
     * A get method to obtain all the users
     *
     * @return a list with all the users
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        List<User> users = null;
        try {
            LOGGER.info("Searching the users");
            users = ejb.findAll();

        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        return users;
    }

}
