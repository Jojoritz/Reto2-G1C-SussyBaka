/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import client.logic.exception.BusinessLogicException;
import javax.ws.rs.core.GenericType;

/**
 * This is a interface to separate the layers between view and models
 *
 * @author Henri
 */
public interface PostController {

    /**
     * This method returns a List of Post created on that date
     *
     * @param <T> The returned object, this will probably be a list of post
     * @param responseType The returned object, this will probably be a list of
     * post
     * @param courseId Course ID
     * @param date Date to filter
     * @return The returned object, this will probably be a list of post
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public <T> T findByDate(GenericType<T> responseType, String courseId, String date) throws BusinessLogicException;

    /**
     * This method returns a List of Post created on a date range
     *
     *
     * @param <T> The returned object, this will probably be a list of post
     * @param responseType The returned object, this will probably be a list of
     * post
     * @param courseId Course ID
     * @param from Date start
     * @param to Date end
     * @return The returned object, this will probably be a list of post
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public <T> T findByDateRange(GenericType<T> responseType, String courseId, String from, String to) throws BusinessLogicException;

    /**
     * This method returns a Post using a ID
     *
     * @param <T> The returned object, this will probably be a list of post
     * @param responseType The returned object, this will probably be a list of
     * post
     * @param id Find post by ID
     * @return Returns a post found by the ID
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public <T> T find(GenericType<T> responseType, String id) throws BusinessLogicException;

    /**
     * This method create a post object
     *
     * @param requestEntity The post object to be created on the server
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public void create(Object requestEntity) throws BusinessLogicException;

    /**
     * This method updates a post object
     *
     * @param requestEntity The post Object to be updated on the server
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public void edit(Object requestEntity) throws BusinessLogicException;

    /**
     * This method returns a List of Post filtering using the title name
     *
     * @param <T> This generic type will be a list of Post
     * @param responseType The object that this method will return
     * @param title The title to be used as filter
     * @param courseId The course ID on where to get all the post
     * @return Returns a List of post
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public <T> T findByTitle(GenericType<T> responseType, String courseId, String title) throws BusinessLogicException;

    /**
     * This method deletes the post using his ID
     *
     * @param id The ID of the post that will be removed
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public void remove(String id) throws BusinessLogicException;

    /**
     * This gets all the posts from a course
     *
     * @param <T> This generic type will be a list of Post
     * @param responseType The object that this method will return
     * @param courseId The course ID on where to get all the post
     * @return Returns a List of post
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public <T> T getCoursePosts(GenericType<T> responseType, String courseId) throws BusinessLogicException;

    /**
     * Closes the connection
     */
    public void close();

}
