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
     * @throws BusinessLogicException if an error happened while reseting the passwor
     */
    public void resetPassword_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A REST client method for reseting the password passwing the data in JSON
     * format
     *
     * @param requestEntity the entity we want to change the password
     * @throws BusinessLogicException if any error happened while reseting the password
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
     * @throws BusinessLogicException if any error happened while getting the user with the id
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
     * @throws BusinessLogicException if any error happened while searching the user with the id
     */
    public <T> T getUser_JSON(GenericType<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful client put method for modify a teacher with the id passwing the
     * data in XML format
     *
     * @param requestEntity the teacher to modfy
     * @throws BusinessLogicException if any error happened
     */
    public void modifyTeacher_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client put method for modify a teacher with the id passwing the
     * data in XML format
     *
     * @param requestEntity the teacher to modfy
     * @throws BusinessLogicException if any error happened
     */
    public void modifyTeacher_JSON(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client get method for getting all the users in XML format
     *
     * @param responseType the type of the response
     * @throws BusinessLogicException if any error happened
     */
    public <T> T findAll_XML(GenericType<T> responseType) throws BusinessLogicException;

    /**
     * A RESTful client get method for getting all the users in XML format
     *
     * @param responseType the type of the response
     * @throws BusinessLogicException if any error happened
     */
    public <T> T findAll_JSON(GenericType<T> responseType) throws BusinessLogicException;

    /**
     * A RESTful client put method for modify a student passing the information
     * in XML format
     *
     * @param requestEntity the entity to update
     * @throws BusinessLogicException if any error happened
     */
    public void modifyStudent_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client put method for modify a student passing the information
     * in JSON format
     *
     * @param requestEntity the entity to update
     * @throws BusinessLogicException if any error happened
     */
    public void modifyStudent_JSON(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client get method for get a student by id in XML format
     *
     * @param <T> The type of the response type
     * @param responseType the response type
     * @param id the id of the student
     * @return the student with that id
     * @throws BusinessLogicException if any error happened or if the student
     * don't exist
     */

    public <T> T getStudent_XML(Class<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful client get method for get a student by id in JSON format
     *
     * @param <T> The type of the response type
     * @param responseType the response type
     * @param id the id of the student
     * @return the student with that id
     * @throws BusinessLogicException if any error happened or if the student
     * don't exist
     */
    public <T> T getStudent_JSON(Class<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful client put method for modifying a user
     *
     * @param requestEntity the entity to modify
     * @throws BusinessLogicException if any error happened
     */
    public void modifyUser_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client put method for modifying a user
     *
     * @param requestEntity the entity to modify
     * @throws BusinessLogicException if any error happened
     */
    public void modifyUser_JSON(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client get method for obtaining a teacher by the id
     *
     * @param <T> the type of the response type
     * @param responseType the type of the response
     * @param id the id of the teacher
     * @return the teacher with that id
     * @throws BusinessLogicException if any error happened
     */
    public <T> T getTeacher_XML(Class<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful client get method for obtaining a teacher by the id
     *
     * @param <T> the type of the response type
     * @param responseType the type of the response
     * @param id the id of the teacher
     * @return the teacher with that id
     * @throws BusinessLogicException if any error happened
     */
    public <T> T getTeacher_JSON(Class<T> responseType, String id) throws BusinessLogicException;

    /**
     * A RESTful client delete methdo for deleting a user
     *
     * @param id the id of the user to delete
     * @throws BusinessLogicException if any error happened
     */
    public void removeUser(String id) throws BusinessLogicException;

    /**
     * A RESTful client get method that obtain a user with login and the
     * password
     *
     * @param <T> The type of the response type
     * @param responseType the type of the response
     * @param login the login of the user to search
     * @param password the password of the user to search
     * @return the user with that user and password
     * @throws BusinessLogicException if any error happened
     */
    public <T> T signIn_XML(Class<T> responseType, String login, String password) throws BusinessLogicException;

    /**
     * A RESTful client get method that obtain a user with login and the
     * password
     *
     * @param <T> The type of the response type
     * @param responseType the type of the response
     * @param login the login of the user to search
     * @param password the password of the user to search
     * @return the user with that user and password
     * @throws BusinessLogicException if any error happened
     */
    public <T> T signIn_JSON(Class<T> responseType, String login, String password) throws BusinessLogicException;

    /**
     * A RESTful client create method that creates a user
     *
     * @param requestEntity the user to create
     * @throws BusinessLogicException if any error happened
     */
    public void createUser_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client create method that creates a user
     *
     * @param requestEntity the user to create
     * @throws BusinessLogicException if any error happened
     */
    public void createUser_JSON(Object requestEntity) throws BusinessLogicException;
/**
     * A RESTful client create method that creates a student
     * @param requestEntity the student to create
     * @throws BusinessLogicException if any error happened
     */
    public void createStudent_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client create method that creates a student
     * @param requestEntity the student to create
     * @throws BusinessLogicException if any error happened
     */
    public void createStudent_JSON(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client create method that creates a student
     * @param requestEntity the student to create
     * @throws BusinessLogicException if any error happened
     */
    public void createTeacher_XML(Object requestEntity) throws BusinessLogicException;

    /**
     * A RESTful client create method that creates a teacher
     * @param requestEntity the teacher to create
     * @throws BusinessLogicException if any error happened
     */
    public void createTeacher_JSON(Object requestEntity) throws BusinessLogicException;

    /**
     * A method for closing the connection with the server RESTful service
     */
    public void close();

}
