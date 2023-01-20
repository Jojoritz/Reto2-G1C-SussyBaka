/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb.interfaces;

import java.util.List;
import server.entities.User;
import server.exception.ReadException;
import javax.ejb.Local;
import server.entities.Course;
import server.entities.Student;
import server.entities.Subject;
import server.entities.Teacher;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.UpdateException;

/**
 * The interface that extends to add user use case methods
 *
 * @author ioritz
 */
@Local
public interface UserEJBLocal {

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
     * @param user_id the data of the entity to remove
     * @throws DeleteException If the creation method threw an exception
     */
    public void remove(Integer user_id) throws DeleteException;

    /**
     * Finds the entity value using the primary key object passed by parameter
     *
     * @param login The login of the user
     * @param password The password of the user
     * @return Returns the entity found by using the primary key, can be NULL
     * @throws ReadException If the read
     */
    public User signIn(String login, String password) throws ReadException;

    public User find(Integer id) throws ReadException;

    /**
     * Obtains a list with all the users
     *
     * @return A list with all the users
     * @throws ReadException if any error happends when searchin the users
     */
    public List<User> findAll() throws ReadException;
    
    /**
     * A method to reset the user password
     * @param email the email of the user that wants the password reset
     * @throws UpdateException if any error happends while reseting the password
     */
    public void resetPassword(String email) throws UpdateException;

}
