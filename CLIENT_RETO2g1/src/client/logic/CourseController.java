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

    /**
     * A RESTfull find method to find a course using the Date in XML format
     *
     * @param <T>
     * @param responseType The entity to find
     * @param startDate The parameter to use to find
     * @return A list of Courses with the date
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public <T> T findByDate_XML(GenericType<T> responseType, String startDate) throws ClientErrorException;

    /**
     * A RESTfull find method for find a course using the Date in JSON format
     *
     * @param <T>
     * @param responseType The entity to find
     * @param startDate The parameter to use to find
     * @return A list of Courses with the date
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public <T> T findByDate_JSON(GenericType<T> responseType, String startDate) throws ClientErrorException;

    /**
     * A RESTfull update method that updates course data passing the data in XML
     * format
     *
     * @param requestEntity The entity to update with the updated data
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public void edit_XML(Object requestEntity) throws ClientErrorException;

    /**
     * A RESTfull update method that updates course data passing the data in XML
     * format
     *
     * @param requestEntity The entity to update with the updated data
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public void edit_JSON(Object requestEntity) throws ClientErrorException;

    /**
     * A RESTfull find method to find a Course in XML format
     *
     * @param <T>
     * @param responseType The entity to find
     * @param id The parameter to use to find
     * @return A Course
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public <T> T find_XML(GenericType<T> responseType, String id) throws ClientErrorException;

    /**
     * A RESTfull find method to find a Course in JSON format
     *
     * @param <T>
     * @param responseType The entity to find
     * @param id The parameter to use to find
     * @return A Course
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public <T> T find_JSON(GenericType<T> responseType, String id) throws ClientErrorException;

    /**
     * A RESTful create method for create the entity passing the data in XML
     * format
     *
     * @param requestEntity The Course to create
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public void create_XML(Object requestEntity) throws ClientErrorException;

    /**
     * A RESTful create method for create the entity passing the data in JSON
     * format
     *
     * @param requestEntity The Course to create
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public void create_JSON(Object requestEntity) throws ClientErrorException;

    /**
     * A RESTfull find method to find a course using the name in XML format
     *
     * @param <T>
     * @param responseType The entity to find
     * @param name The parameter to use to find
     * @return A list of Courses with the date
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public <T> T findByName_XML(GenericType<T> responseType, String name) throws ClientErrorException;

    /**
     * A RESTfull find method to find a course using the name in JSON format
     *
     * @param <T>
     * @param responseType The entity to find
     * @param name The parameter to use to find
     * @return A list of Courses with the date
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public <T> T findByName_JSON(GenericType<T> responseType, String name) throws ClientErrorException;

    /**
     * A RESTfull find method to find all the COurses
     *
     * @param <T>
     * @param responseType The entity to find
     * @return A list of all the courses
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public <T> T findAll_XML(GenericType<T> responseType) throws ClientErrorException;

    /**
     * A RESTfull find method to find all the COurses
     *
     * @param <T>
     * @param responseType The entity to find
     * @return A list of all the courses
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public <T> T findAll_JSON(GenericType<T> responseType) throws ClientErrorException;

    /**
     * A RESTfull remove method to Delete a Course
     *
     * @param id The parameter to find the course
     * @throws ClientErrorException If any error happened while modifying the
     * Course
     */
    public void remove(String id) throws ClientErrorException;

    /**
     * A method that close the connection with the server
     */
    public void close();
}
