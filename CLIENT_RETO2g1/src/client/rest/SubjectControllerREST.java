/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.rest;

import client.logic.SubjectController;
import client.logic.exception.BusinessLogicException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:SubjectFacadeREST
 * [entities.subject]<br>
 * USAGE:
 * <pre>
 *        SubjectControllerREST client = new SubjectControllerREST();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author iorit
 */
public class SubjectControllerREST implements SubjectController {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = ResourceBundle.getBundle("client.config").getString("restful_server");
    private static final Logger LOGGER = Logger.getLogger(SubjectControllerREST.class.getName());

    public SubjectControllerREST() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.subject");
    }

    public void createSubject_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Creating a subject");
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));

        } catch (Exception e) {
            LOGGER.severe("An error happened while creating the subject: " + e.getMessage());
            throw new BusinessLogicException("An errro happened while creating the subject");
        }
    }

    public void createSubject_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Creating a subject");
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                    .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));

        } catch (Exception e) {
            LOGGER.severe("An error happened while creating the subject: " + e.getMessage());
            throw new BusinessLogicException("An error happened while creating the subject");
        }
    }

    public void removeSubject(String id) throws BusinessLogicException {
        try {
            LOGGER.info("Removing the subject");
            webTarget.path(java.text.MessageFormat.format("subject/{0}", new Object[]{id})).request().delete();

        } catch (Exception e) {
            LOGGER.severe("An error happened while deleting the subject: " + e.getMessage());
            throw new BusinessLogicException("An error happened while deleting the subject");
        }

    }

    public <T> T searchByName_XML(GenericType<T> responseType, String nameParam) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the subject by the name");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("subject/name/{0}", new Object[]{nameParam}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);

        } catch (Exception e) {
            LOGGER.severe("An error happened while searching the subject by the name: " + e.getMessage());
            throw new BusinessLogicException("An error happened while searching the subject by the name");
        }

    }

    public <T> T searchByName_JSON(GenericType<T> responseType, String nameParam) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the subject by the name");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("subject/name/{0}", new Object[]{nameParam}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error happened while searching the subject by the name: " + e.getMessage());
            throw new BusinessLogicException("An error happened while searching the subject by the name");

        }

    }

    public <T> T find_XML(Class<T> responseType, String id) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the subject by the id");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("subject/{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error happened while searhing the subject by the id: " + e.getMessage());
            throw new BusinessLogicException("An erro happened while searching the subject by the id");
        }

    }

    public <T> T find_JSON(Class<T> responseType, String id) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the subject by the id");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("subject/{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error happened while searching the subject by the id: " + e.getMessage());
            throw new BusinessLogicException("An error happened while searching the subject by the id");
        }

    }

    public <T> T searchByLevel_XML(GenericType<T> responseType, String level) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the subject by the level");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("subject/level/{0}", new Object[]{level}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);

        } catch (Exception e) {
            LOGGER.severe("An error ocurred while searching the subject by the level: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while searching the subject by the level");
        }

    }

    public <T> T searchByLevel_JSON(GenericType<T> responseType, String level) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the subject by the level");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("subject/level/{0}", new Object[]{level}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while searching the subejct by the level: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while searching the subject by the level");
        }

    }

    public void modifySubject_XML(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Modifying the subject");
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                    .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
        } catch (Exception e) {
            LOGGER.severe("An error ocurred while modifying the subject: " + e.getMessage());
            throw new BusinessLogicException("An error ocurred while modifying the subject");
        }
        
    }

    public void modifySubject_JSON(Object requestEntity) throws BusinessLogicException {
        try {
            LOGGER.info("Modifying the subject");
             webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                     .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            
        } catch (Exception e) {
            LOGGER.severe("An error happened while modifying the subject: " + e.getMessage());
            throw new BusinessLogicException("An error happened while modifying the subject");
        }
       
    }

    public <T> T searchByType_XML(GenericType<T> responseType, String type) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the subject by the type");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("subject/type/{0}", new Object[]{type}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.info("An error happened while searching the subject by the type: " + e.getMessage());
            throw new BusinessLogicException("An error happened while searching the subject by the type");
        }
        
    }

    public <T> T searchByType_JSON(GenericType<T> responseType, String type) throws BusinessLogicException {
        try {
            LOGGER.info("Searching the subject by the type");
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("subject/type/{0}", new Object[]{type}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error happened while searching the subject by the type: " + e.getMessage());
            throw new BusinessLogicException("An error happened while searching the subject by the type");
        }
        
    }

    public <T> T findAll_XML(GenericType<T> responseType) throws BusinessLogicException {
        try {
            LOGGER.info("Getting all the subjects");
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.severe("An error happened while ");
            throw new BusinessLogicException("An error happened while getting all the subjects");
        }
        
    }

    public <T> T findAll_JSON(GenericType<T> responseType) throws BusinessLogicException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }

}
