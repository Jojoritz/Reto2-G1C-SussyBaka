/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;
import client.logic.exception.BusinessLogicException;
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
     * @throws BusinessLogicException if any error happened when process
     */

    public void createSubject_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful create method for create the entity passing the data in JSON
     * format
     *
     * @param requestEntity the subject to create
     * @throws BusinessLogicException
     */
    public void createSubject_JSON(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful remove method for delete the entity with a concret id
     *
     * @param id the id of the subject that want delete
     * @throws BusinessLogicException
     */
    public void removeSubject(String id) throws BusinessLogicException;

    /**
     * A RESTful get mehtod that search a subject by name passing the data in
     * XML format
     *
     * @param <T> The type of the response type
     * @param responseType the response type
     * @param nameParam the name to search by
     * @return A subject searched by name
     * @throws BusinessLogicException
     */
    public <T> T searchByName_XML(Class<T> responseType, String nameParam) throws BusinessLogicException;

    /**
     * A RESTful get method that search a subject by name passing the data in
     * JSON format
     *
     * @param <T> The type of the response type
     * @param responseType the response type
     * @param nameParam the name to search by
     * @return A subject searched by name
     * @throws BusinessLogicException
     */
    public <T> T searchByName_JSON(Class<T> responseType, String nameParam) throws BusinessLogicException;

    /**
     * A RESTful get method that search a subject by id passing the data in XML
     * format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param id the id of the subject that want find
     * @return A subject with the id
     * @throws BusinessLogicException
     */
    public <T> T find_XML(Class<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful get method that search a subject by id passing the data in JSON
     * format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param id the id of the subject that want find
     * @return A subject with the id
     * @throws BusinessLogicException
     */
    public <T> T find_JSON(Class<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful get method that search subjects by the level passing the data
     * in XML format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param level the level of the subject that want find
     * @return A list of subjects with the level
     * @throws BusinessLogicException
     */

    public <T> T searchByLevel_XML(Class<T> responseType, String level) throws BusinessLogicException;

    /**
     * A RESTful get method that subjects by the level passing the data
     * in JSON format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param level the level of the subject that want find
     * @return A list of subjects with the level
     * @throws BusinessLogicException
     */

    public <T> T searchByLevel_JSON(Class<T> responseType, String level) throws BusinessLogicException;

    /**
     * A RESTful update method that updates subject data passing the data in XML
     * format
     *
     * @param requestEntity the entity to update with the updated data
     * @throws BusinessLogicException
     */
    public void modifySubject_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful update method that updates subject data passing the data in JSON
     * format
     *
     * @param requestEntity the entity to update with the updated data
     * @throws BusinessLogicException
     */
    public void modifySubject_JSON(Object requestEntity) throws BusinessLogicException;
/**
     * A RESTful get method that search subjects by the type passing the data
     * in XML format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param type the type of the subject that want find
     * @return Subject list with the type
     * @throws BusinessLogicException
     */
    public <T> T searchByType_XML(Class<T> responseType, String type) throws BusinessLogicException;
/**
     * A RESTful get method that search subjects by the type passing the data
     * in JSON format
     *
     * @param <T> The type of the response type
     * @param responseType The response type
     * @param type the type of the subject that want find
     * @return Subject list with the type
     * @throws BusinessLogicException
     */
    
    public <T> T searchByType_JSON(Class<T> responseType, String type) throws BusinessLogicException;
    /**
     * A RESTfuo get method that gets all subjects in XML format
     * @param <T> The type of the response type
     * @param responseType the response type
     * @return A collection of subjects
     * @throws BusinessLogicException 
     */
    public <T> T findAll_XML(GenericType<T> responseType) throws BusinessLogicException;
     /**
     * A RESTfuo get method that gets all subjects in JSON format
     * @param <T> The type of the response type
     * @param responseType the response type
     * @return A collection of subjects
     * @throws BusinessLogicException 
     */

    public <T> T findAll_JSON(GenericType<T> responseType) throws BusinessLogicException;

    /**
     * A method that close the connection with the server
     */
    public void close();

}
