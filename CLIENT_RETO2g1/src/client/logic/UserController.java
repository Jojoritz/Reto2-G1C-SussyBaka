/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import client.logic.exception.BusinessLogicException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author iorit
 */
public interface UserController {

    /**
     * A REST client method for reseting the password passwing the data in XML
     * format
     *
     * @param requestEntity the entity we want to change the password
     * @throws ClientErrorException
     */
    public void resetPassword_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A REST client method for reseting the password passwing the data in JSON
     * format
     *
     * @param requestEntity the entity we want to change the password
     * @throws ClientErrorException
     */
    public void resetPassword_JSON(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client get method for obtain a user with the id passwing the
     * data in XML format
     *
     * @param <T> The type of the response type
     * @param responseType the type of the response that we expect to obtain
     * after executing this method
     * @param id the id of the user we want to obtain
     * @return the user with the id we want
     * @throws ClientErrorException
     */
    public <T> T getUser_XML(GenericType<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful client get method for obtain a user with the id passwing the
     * data in JSON format
     *
     * @param <T> The type of the response type
     * @param responseType the type of the response that we expect to obtain
     * after executing this method
     * @param id the id of the user we want to obtain
     * @return the user with the id we want
     * @throws ClientErrorException
     */
    public <T> T getUser_JSON(GenericType<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful client get method for obtain a user with the id passwing the
     * data in XML format
     *
     * @param <T> The type of the response type
     * @param responseType the type of the response that we expect to obtain
     * after executing this method
     * @param id the id of the user we want to obtain
     * @return the user with the id we want
     * @throws ClientErrorException
     */
    public void modifyTeacher_XML(Object requestEntity) throws BusinessLogicException;

    public void modifyTeacher_JSON(Object requestEntity) throws BusinessLogicException;

    public <T> T findAll_XML(GenericType<T> responseType) throws BusinessLogicException;

    public <T> T findAll_JSON(GenericType<T> responseType) throws BusinessLogicException;

    public void modifyStudent_XML(Object requestEntity) throws BusinessLogicException;

    public void modifyStudent_JSON(Object requestEntity) throws BusinessLogicException;

    public <T> T getStudent_XML(GenericType<T> responseType, String id) throws BusinessLogicException;

    public <T> T getStudent_JSON(GenericType<T> responseType, String id) throws BusinessLogicException;

    public void modifyUser_XML(Object requestEntity) throws BusinessLogicException;

    public void modifyUser_JSON(Object requestEntity) throws BusinessLogicException;

    public <T> T getTeacher_XML(GenericType<T> responseType, String id) throws BusinessLogicException;

    public <T> T getTeacher_JSON(GenericType<T> responseType, String id) throws BusinessLogicException;

    public void removeUser(String id) throws BusinessLogicException;

    public <T> T signIn_XML(GenericType<T> responseType, String login, String password) throws BusinessLogicException;

    public <T> T signIn_JSON(GenericType<T> responseType, String login, String password) throws BusinessLogicException;

    public void createUser_XML(Object requestEntity) throws BusinessLogicException;

    public void createUser_JSON(Object requestEntity) throws BusinessLogicException;

    public void createStudent_XML(Object requestEntity) throws BusinessLogicException;

    public void createStudent_JSON(Object requestEntity) throws BusinessLogicException;

    public void createTeacher_XML(Object requestEntity) throws BusinessLogicException;

    public void createTeacher_JSON(Object requestEntity) throws BusinessLogicException;

    /**
     * A method for closing the connection with the server RESTful service
     */
    public void close();

}
