/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Joritz
 */
public interface CourseController {
    public <T> T findByDate_XML(GenericType<T> responseType, String startDate) throws ClientErrorException;
    
    public <T> T findByDate_JSON(GenericType<T> responseType, String startDate) throws ClientErrorException;
    
    public void edit_XML(Object requestEntity) throws ClientErrorException;
    
    public void edit_JSON(Object requestEntity) throws ClientErrorException;
    
    public <T> T find_XML(GenericType<T> responseType, String id) throws ClientErrorException;
    
    public <T> T find_JSON(GenericType<T> responseType, String id) throws ClientErrorException;
    
    public void create_XML(Object requestEntity) throws ClientErrorException;
    
    public void create_JSON(Object requestEntity) throws ClientErrorException;
    
    public <T> T findByName_XML(GenericType<T> responseType, String name) throws ClientErrorException;
    
    public <T> T findByName_JSON(GenericType<T> responseType, String name) throws ClientErrorException;
    
    public <T> T findAll_XML(GenericType<T> responseType) throws ClientErrorException;
    
    public <T> T findAll_JSON(GenericType<T> responseType) throws ClientErrorException;
    
    public void remove(String id) throws ClientErrorException;
    
    public void close();
}
