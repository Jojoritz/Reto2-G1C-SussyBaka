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
     * A RESTful create method for create the entity passing the data in XML format
     * @param requestEntity the subject to create
     * @throws ClientErrorException if any error happened when process
     */
    
    public void createSubject_XML(Object requestEntity) throws ClientErrorException;
    /**
     * A RESTful create method for create the entity passing the data in JSON format
     * @param requestEntity the subject to create
     * @throws ClientErrorException 
     */
    public void createSubject_JSON(Object requestEntity) throws ClientErrorException;
    /**
     * A RESTful remove method for delete the entity with a concret id
     * @param id the id of the subject that want delete
     * @throws ClientErrorException 
     */
    public void removeSubject(String id) throws ClientErrorException;
    /**
     * A RESTful get mehtod that search a subject by name passing the data in XML format
     * @param <T> The respon
     * @param responseType
     * @param nameParam
     * @return
     * @throws ClientErrorException 
     */
    public <T> T searchByName_XML(Class<T> responseType, String nameParam) throws ClientErrorException;
    /**
     * A RESTful get method that search a subject by name passing the data in JSON format
     * @param <T>
     * @param responseType
     * @param nameParam
     * @return
     * @throws ClientErrorException 
     */
    public <T> T searchByName_JSON(Class<T> responseType, String nameParam) throws ClientErrorException;
    public <T> T find_XML(Class<T> responseType, String id) throws ClientErrorException;
    public <T> T find_JSON(Class<T> responseType, String id) throws ClientErrorException;
    
    public <T> T searchByLevel_XML(Class<T> responseType, String level) throws ClientErrorException;
    

    public <T> T searchByLevel_JSON(Class<T> responseType, String level) throws ClientErrorException;

    public void modifySubject_XML(Object requestEntity) throws ClientErrorException;

    public void modifySubject_JSON(Object requestEntity) throws ClientErrorException;
    public <T> T searchByType_XML(Class<T> responseType, String type) throws ClientErrorException;
       
    public <T> T searchByType_JSON(Class<T> responseType, String type) throws ClientErrorException;
        

    public <T> T findAll_XML(GenericType<T> responseType) throws ClientErrorException;
    public <T> T findAll_JSON(Class<T> responseType) throws ClientErrorException;
    public void close();
      
}
