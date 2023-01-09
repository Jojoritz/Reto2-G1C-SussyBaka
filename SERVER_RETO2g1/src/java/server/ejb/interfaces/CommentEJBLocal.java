/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb.interfaces;

import server.entities.Comment;
import server.entities.Post;
import server.exception.ReadException;
import java.util.List;
import javax.ejb.Local;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.UpdateException;

/**
 * This {@code Abstract class} extends the {@link AbstractEJB} to implement
 * necessary methods for the entity {@link Post}
 *
 *
 *
 * @author yeguo
 */
@Local
public interface CommentEJBLocal {

    /**
     * Gets the comments that the post has
     *
     * @param id The ID of the post from where to get the comments {@link Post}
     * @return Returns a List with all the comments from the post
     * @throws ReadException Throws exception if there was any error when
     * getting the comments
     */
    public abstract List<Comment> getComments(Integer id) throws ReadException;

    
    /**
     * Creates/inserts the data of the entity passed
     *
     * @param entity
     * @throws CreateException If the creation method threw an exception
     */
    public void create(Comment entity) throws CreateException;

    /**
     * Edits/Modify the data of the entity passed
     *
     * @param entity
     * @throws UpdateException If the creation method threw an exception
     */
    public void edit(Comment entity) throws UpdateException;

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param entity
     * @throws DeleteException If the creation method threw an exception
     */
    public void remove(Comment entity) throws DeleteException;

    /**
     * Finds the entity value using the primary key object passed by parameter
     *
     * @param obj Primary key of the entity
     * @return Returns the entity found by using the primary key, can be NULL
     * @throws ReadException If the read
     */
    public Comment find(Object obj) throws ReadException;

}
