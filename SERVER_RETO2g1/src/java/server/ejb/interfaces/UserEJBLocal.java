/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb.interfaces;

import server.entities.User;
import server.exception.ReadException;
import javax.ejb.Local;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.UpdateException;

/**
 * The interface that extends to add user use case methods
 * @author ioritz
 */
@Local
public interface UserEJBLocal{
    /**
     * A method to obtain all data from the users and the relationship of the user
     * @param user the user we are searching for
     * @return The user with the data, relationships included
     * @throws ReadException if the login data is wrong or dont exist
     */
    public User getUserRelationshipsData(Integer id) throws ReadException;
    
     /**
     * Creates/inserts the data of the entity passed
     *
     * @param user the data of the user to create
     * @throws CreateException If the creation method threw an exception
     */
    public void create(User user) throws CreateException;

    /**
     * Edits/Modify the data of the entity passed
     *
     * @param user the data of the user to edit
     * @throws UpdateException If the creation method threw an exception
     */
    public void edit(User user) throws UpdateException;

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param user the data of the entity to remove
     * @throws DeleteException If the creation method threw an exception
     */
    public void remove(Integer user_id) throws DeleteException;
    

    /**
     * Finds the entity value using the primary key object passed by parameter
     *
     * @param user Primary key of the entity
     * @return Returns the entity found by using the primary key, can be NULL
     * @throws ReadException If the read
     */
    public User signIn(String login) throws ReadException;
}
