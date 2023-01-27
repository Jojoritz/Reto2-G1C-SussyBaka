/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.rest;

import client.logic.UserController;
import client.logic.exception.BusinessLogicException;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:UserFacadeREST
 * [entities.user]<br>
 * USAGE:
 * <pre>
 *        UserControllerREST client = new UserControllerREST();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Henri
 */
public class UserControllerREST implements UserController {
    
    private static final Logger LOGGER = Logger.getLogger(UserControllerREST.class.getName());
    
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SERVER_RETO2g1/webresources";
    
    public UserControllerREST() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.user");
    }
    
    @Override
    public void resetPassword_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Reseting the password");
            webTarget.path("user/reset/password/mail").request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when trying to reset password" + e.getMessage());
            throw new BusinessLogicException("An error ocurred whe trying to reset password");
        }
        
    }
    
    @Override
    public void resetPassword_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Reseting the password");
            webTarget.path("user/reset/password/mail").request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when trying to reset the password " + e.getMessage());
            throw new BusinessLogicException("An error ocurred when trying to reset the passwor ");
        }
    }
    
    @Override
    public <T> T getUser_XML(GenericType<T> responseType, String id) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the user ny id");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("user/{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when trying to get the user " + e.getMessage());
            throw new BusinessLogicException("An error ocurred when trying to get the user ");
        }
        
    }
    
    @Override
    public <T> T getUser_JSON(GenericType<T> responseType, String id) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the user by Id");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("user/{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when trying to get the user " + e.getMessage());
            throw new BusinessLogicException("An error ocurred when trying to get the user ");
        }
        
    }
    
    @Override
    public void modifyTeacher_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Modifying the teacher");
            webTarget.path("user/teacher").request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when trying to update the user: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred when trying to update the user");
        }
    }
    
    @Override
    public void modifyTeacher_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Modifying the teacher");
            webTarget.path("user/teacher").request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when modfying the user: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred when modifying the user");
        }
    }
    
    @Override
    public <T> T findAll_XML(GenericType<T> responseType) throws BusinessLogicException {
        try {
            LOGGER.info("Searching for all the users");
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while searching all the users: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while searching all the users");
        }
        
    }
    
    @Override
    public <T> T findAll_JSON(GenericType<T> responseType) throws BusinessLogicException {
        try {
            LOGGER.info("Searching for all the users");
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while searching for all the users: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while searching for all the users");
        }
        
    }
    
    @Override
    public void modifyStudent_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Modifying the student");
            webTarget.path("user/student").request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while modifying the student: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while modifying the student");
        }
    }
    
    @Override
    public void modifyStudent_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Modifying the student");
            webTarget.path("user/student").request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while modifying the student: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while modifying the student");
        }
    }
    
    @Override
    public <T> T getStudent_XML(GenericType<T> responseType, String id) throws BusinessLogicException {
        try {
            LOGGER.info("Searching a student with id");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("user/student/{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to modify the student: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while trying to modify the student");
        }
        
    }
    
    @Override
    public <T> T getStudent_JSON(GenericType<T> responseType, String id) throws BusinessLogicException {
        try {
            LOGGER.info("Searching a student with id");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("user/student/{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to modify the student: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while trying to modify the student");
        }
        
    }
    
    @Override
    public void modifyUser_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Modifying the user");
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while modifying the user: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while modifying the user");
        }
    }
    
    @Override
    public void modifyUser_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Mofying the user");
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while modifying the user: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while modifying the user");
        }
    }
    
    @Override
    public <T> T getTeacher_XML(GenericType<T> responseType, String id) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the teacher by id");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("user/teacher/{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while searching the teacher: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while modifying the teacher");
        }
        
    }
    
    @Override
    public <T> T getTeacher_JSON(GenericType<T> responseType, String id) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the teacher by id");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("user/teacher/{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while searching the teacher: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while modifying the teacher");
        }
    }
    
    @Override
    public void removeUser(String id) throws BusinessLogicException {
        try {
            LOGGER.info("Deleting the user");
            webTarget.path(java.text.MessageFormat.format("user/{0}", new Object[]{id})).request().delete();
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to delete the user" + e.getMessage());
            throw new BusinessLogicException("An error ocurred while trying to delete the user");
        }
    }
    
    @Override
    public <T> T signIn_XML(GenericType<T> responseType, String login, String password) throws BusinessLogicException {
        try {
            LOGGER.info("Trying to signIn");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("user/login/{0}/{1}", new Object[]{login, password}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to signIn: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while trying to signIn");
        }
        
    }
    
    @Override
    public <T> T signIn_JSON(GenericType<T> responseType, String login, String password) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("user/login/{0}/{1}", new Object[]{login, password}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to signIn: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while trying to signIn");
        }
        
    }
    
    @Override
    public void createUser_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Creating the user");
            webTarget.path("user").request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to create the user " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while trying to create the user");
        }
    }
    
    @Override
    public void createUser_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Creating the user");
            webTarget.path("user").request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to create the user " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while trying to create the user");
        }
    }
    
    @Override
    public void createStudent_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Creating the student");
            webTarget.path("user/student").request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to create the student: " + e.getMessage());
            throw new BusinessLogicException("An error ocurrred whilet trying to create the student");
        }
    }
    
    @Override
    public void createStudent_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Creating the student");
            webTarget.path("user/student").request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to create the student: " + e.getMessage());
            throw new BusinessLogicException("An error ocurrred whilet trying to create the student");
        }
    }
    
    @Override
    public void createTeacher_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Creating the teacher");
            webTarget.path("user/teacher").request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to create the teacher: " + e.getMessage());
            throw new BusinessLogicException("An error ocurrred whilet trying to create the teacher");
        }
    }
    
    @Override
    public void createTeacher_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Creating the teacher");
            webTarget.path("user/teacher").request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while trying to create the teacher: " + e.getMessage());
            throw new BusinessLogicException("An error ocurrred whilet trying to create the teacher");
        }
    }
    
    @Override
    public void close() {
        LOGGER.info("Closing the connection with the server");
        client.close();
    }
    
}
