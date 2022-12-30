/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ejb.interfaces.InterfaceEJBCRUD;
import entities.User;
import exception.CreateException;
import java.util.List;
import java.util.logging.Level;
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
import java.util.logging.Logger;
import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author 2dam
 */
@Path("entities.user")
public class UserFacadeREST{
    /**
     * EJB object with the busines logic
     */
    
    @EJB
    private InterfaceEJBCRUD ejb;
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
            ejb.
            LOGGER.info("Creating a user");
            ejb.create(user);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modifyUser(User user) {
        
    }
}
