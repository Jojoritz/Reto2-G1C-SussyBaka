/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author iorit
 */
public interface SubjectController {

    /**
     * A RESTful create method for create the entity passing the data in XML
     * format
     *
     * @param requestEntity the subject to create
     * @throws ClientErrorException if any error happened when process
     */

    public void createSubject_XML(Object requestEntity) throws ClientErrorException;

    /**
     * A RESTful create method for create the entity passing the data in JSON
     * format
     *
     * @param requestEntity the subject to create
     * @throws ClientErrorException
     */
    public void createSubject_JSON(Object requestEntity) throws ClientErrorException;

    /**
     * A RESTful remove method for delete the entity with a concret id
     *
     * @param id the id of the subject that want delete
     * @throws ClientErrorException
     */
    public void removeSubject(String id) throws ClientErrorException;

    /**
     * A RESTful get mehtod that search a subject by name passing the data in
     * XML format
     *
     * @param <T> The type of the response type
     * @param responseType the response type
     * @param nameParam the name to search by
     * @return A subject searched by name
     * @throws ClientErrorException
     */
    public <T> T searchByName_XML(Class<T> responseType, String nameParam) throws ClientErrorException;

    /**
     * A RESTful get method that search a subject by name passing the data in
     * JSON format
     *
     * @param <T> The type of the response type
     * @param responseType the response type
     * @param nameParam the name to search by
     * @return A subject searched by name
     * @throws ClientErrorException
     */
    public <T> T searchByName_JSON(Class<T> responseType, String nameParam) throws ClientErrorException;

    /**
     * A RESTful get method that search a subject by id passing the data in XML
     * format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param id the id of the subject that want find
     * @return A subject with the id
     * @throws ClientErrorException
     */
    public <T> T find_XML(Class<T> responseType, String id) throws ClientErrorException;

    /**
     * A RESTful get method that search a subject by id passing the data in JSON
     * format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param id the id of the subject that want find
     * @return A subject with the id
     * @throws ClientErrorException
     */
    public <T> T find_JSON(Class<T> responseType, String id) throws ClientErrorException;

    /**
     * A RESTful get method that search subjects by the level passing the data
     * in XML format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param level the level of the subject that want find
     * @return A list of subjects with the level
     * @throws ClientErrorException
     */

    public <T> T searchByLevel_XML(Class<T> responseType, String level) throws ClientErrorException;

    /**
     * A RESTful get method that subjects by the level passing the data
     * in JSON format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param level the level of the subject that want find
     * @return A list of subjects with the level
     * @throws ClientErrorException
     */

    public <T> T searchByLevel_JSON(Class<T> responseType, String level) throws ClientErrorException;

    /**
     * A RESTful update method that updates subject data passing the data in XML
     * format
     *
     * @param requestEntity the entity to update with the updated data
     * @throws ClientErrorException
     */
    public void modifySubject_XML(Object requestEntity) throws ClientErrorException;

    /**
     * A RESTful update method that updates subject data passing the data in JSON
     * format
     *
     * @param requestEntity the entity to update with the updated data
     * @throws ClientErrorException
     */
    public void modifySubject_JSON(Object requestEntity) throws ClientErrorException;
/**
     * A RESTful get method that search subjects by the type passing the data
     * in XML format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param type the type of the subject that want find
     * @return Subject list with the type
     * @throws ClientErrorException
     */
    public <T> T searchByType_XML(Class<T> responseType, String type) throws ClientErrorException;
/**
     * A RESTful get method that search subjects by the type passing the data
     * in JSON format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param type the type of the subject that want find
     * @return Subject list with the type
     * @throws ClientErrorException
     */
    
    public <T> T searchByType_JSON(Class<T> responseType, String type) throws ClientErrorException;
    /**
     * A RESTfuo get method that gets all subjects in XML format
     * @param <T> The type of the response type
     * @param responseType the response type
     * @return A collection of subjects
     * @throws ClientErrorException 
     */
    public <T> T findAll_XML(GenericType<T> responseType) throws ClientErrorException;
     /**
     * A RESTfuo get method that gets all subjects in JSON format
     * @param <T> The type of the response type
     * @param responseType the response type
     * @return A collection of subjects
     * @throws ClientErrorException 
     */

    public <T> T findAll_JSON(GenericType<T> responseType) throws ClientErrorException;

    /**
     * A method that close the connection with the server
     */
    public void close();

}
