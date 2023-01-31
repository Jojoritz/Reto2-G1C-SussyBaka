/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.rest;

import client.logic.CommentController;
import client.logic.exception.BusinessLogicException;
import java.util.ResourceBundle;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:CommentFacadeREST
 * [entities.comment]<br>
 * USAGE:
 * <pre>
 *        CommentControllerRest client = new CommentControllerRest();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * This RESTFul can use XML or JSON, changing the value on the
 * {@code respose_type} on the configuration properties file
 * {@code config.properties}, if NULL/NONE specified it will use XML as default
 *
 * @author Henri
 */
public class CommentControllerREST implements CommentController {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = ResourceBundle.getBundle("client.config").getString("restful_server");
    private static final String RETURN_TYPE = ResourceBundle.getBundle("client.config").getString("response_type");

    public CommentControllerREST() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.comment");
    }

    @Override
    public void edit(Object requestEntity) throws BusinessLogicException {
        try {
            if (ReturnType.JSON.name().equalsIgnoreCase(RETURN_TYPE)) {
                webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            } else {
                webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
            }
        } catch (Exception e) {
            throw new BusinessLogicException(e.getMessage());
        }
    }

    @Override
    public <T> T find(Class<T> responseType, String id) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            if (ReturnType.JSON.name().equalsIgnoreCase(RETURN_TYPE)) {
                return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
            } else {
                return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
            }
        } catch (Exception e) {
            throw new BusinessLogicException(e.getMessage());
        }
    }

    @Override
    public void create(Object requestEntity) throws BusinessLogicException {
        try {
            if (ReturnType.JSON.name().equalsIgnoreCase(RETURN_TYPE)) {
                webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
            } else {
                webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
            }
        } catch (Exception e) {
            throw new BusinessLogicException(e.getMessage());
        }
    }

    @Override
    public <T> T findAll(GenericType<T> responseType, String id) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("post/{0}", new Object[]{id}));
            if (ReturnType.JSON.name().equalsIgnoreCase(RETURN_TYPE)) {
                return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
            } else {
                return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
            }
        } catch (Exception e) {
            throw new BusinessLogicException(e.getMessage());
        }
    }

    @Override
    public void remove(String id) throws BusinessLogicException {
        try {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
        } catch (Exception e) {
            throw new BusinessLogicException(e.getMessage());
        }
    }

    @Override
    public void close() {
        client.close();
    }

}
