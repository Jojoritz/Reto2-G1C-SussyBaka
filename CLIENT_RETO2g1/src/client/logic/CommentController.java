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
 * @author Henri
 */
public interface CommentController {

    /**
     * This method updates de comment object
     *
     *
     * @param requestEntity The comment object that will be update
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public void edit(Object requestEntity) throws BusinessLogicException;

    /**
     * This method finds a comment by his ID
     *
     * @param <T> Generic type, it will be comments
     * @param responseType The object comment as response type
     * @param id The ID of the comment
     * @return The comment object found with his ID
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public <T> T find(Class<T> responseType, String id) throws BusinessLogicException;

    /**
     * This method creates a comment
     *
     * @param requestEntity The comment object to be created
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public void create(Object requestEntity) throws BusinessLogicException;

    /**
     * Gets all comments from a post
     *
     * @param <T> The generic type, this will be a comment
     * @param responseType The reponse object
     * @param id The ID of the post
     * @return List with all the comments found in the post
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public <T> T findAll(GenericType<T> responseType, String id) throws BusinessLogicException;

    /**
     * This method removes a comment using his ID
     *
     * @param id The ID of the comment to be deleted
     * @throws client.logic.exception.BusinessLogicException Exception if
     * something went wrong
     */
    public void remove(String id) throws BusinessLogicException;

    /**
     * Closes the connection
     */
    public void close();
}
