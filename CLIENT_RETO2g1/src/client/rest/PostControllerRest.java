/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.rest;

import client.logic.PostController;
import client.logic.exception.BusinessLogicException;
import java.util.ResourceBundle;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:PostFacadeREST
 * [entities.post]<br>
 * USAGE:
 * <pre>
 *        PostControllerRest client = new PostControllerRest();
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
public class PostControllerRest implements PostController {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = ResourceBundle.getBundle("client.config").getString("restful_server");
    private static final String RETURN_TYPE = ResourceBundle.getBundle("client.config").getString("response_type");

    public PostControllerRest() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.post");
    }

    @Override
    public <T> T findByDate(GenericType<T> responseType, String courseId, String date) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}/date/{1}", new Object[]{courseId, date}));

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
    public <T> T findByDateRange(GenericType<T> responseType, String courseId, String from, String to) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}/date/{1}/{2}", new Object[]{courseId, from, to}));

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
    public <T> T find(GenericType<T> responseType, String id) throws BusinessLogicException {
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
    public <T> T findByTitle(GenericType<T> responseType, String courseId, String title) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}/title/{1}", new Object[]{courseId, title}));
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
    public <T> T getCoursePosts(GenericType<T> responseType, String courseId) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("post/{0}", new Object[]{courseId}));
            if (ReturnType.JSON.name().equalsIgnoreCase(RETURN_TYPE)) {
                return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
            } else {
                return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessLogicException(e.getMessage());
        }
    }

    @Override
    public void close() {
        client.close();
    }

}
