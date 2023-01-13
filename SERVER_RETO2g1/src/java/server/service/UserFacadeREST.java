/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service;

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

/**
 *
 * @author ioritz
 */
@Path("entities.user")
public class UserFacadeREST{
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
     * @param user the user with the data
     */
    @POST
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
     * PUT method to update user
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
     * DELETE method to remove the user
     * @param user the data of user to remove
     */
    @DELETE
    @Path("{user}")
    public void removeUser(@PathParam("user")User user) {
        try {
            LOGGER.info("Deleting the user");
            ejb.remove(user);
        } catch (DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    /**
     * GET method to signIn a user
     * @param xtUser the data of the user to signIn
     * @return the user finded
     */
    @GET
    @Path("user/login/{login}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User signIn(@PathParam("login")String login, @PathParam("password")String password){
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
     * GET method to search a user relationships with other entityes
     * @param user the data of the user to search the relationships
     * @return the user with the data
     */
    @GET
    @Path("user/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserRelationships(@PathParam("id") Integer id){
       User searchedUser = null;
        try {
            LOGGER.info("searching the relationships of the user");
            searchedUser = ejb.getUserRelationshipsData(id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        return searchedUser;
    }
}
