/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb.interfaces;

import java.util.List;
import server.entities.Subject;
import server.exception.ReadException;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.UpdateException;

/**
 *
 * @author ioritz the abstract class for the subject EJB
 */
public interface SubjectEJBLocal{
    /**
     * The method to search the subjects that has this name
     * @param name the data we need to search the subject
     * @return The data of the subject with this name
     * @throws ReadException if any error happened when searching the subject
     */
    public List<Subject> searchByName(String name) throws ReadException;
    /**
     * The method to search the subject of one concret type
     * @param type the data we need to search the subject
     * @return a set with the data of the subjects of searching type
     * @throws ReadException if any error happened when searching the subject
     */
    public List<Subject> searchByType(String type) throws ReadException;
    /**
     * The method to search the subject of on concret level
     * @param level the data we need to search the subject
     * @return a set with the data of the subjects of searching level
     * @throws ReadException if any error happened when searching the subject
     */
    public List<Subject> searchByLevel(String level) throws ReadException;
    
    /**
     * The method to obtain the data of all the subjects
     * @return A set of subjects
     * @throws ReadException  if any error happend when searching the subject data
     */
    public List<Subject> findAll() throws ReadException;
    
      /**
     * Creates/inserts the data of the entity passed
     *
     * @param entity
     * @throws CreateException If the creation method threw an exception
     */
    public void create(Subject subject) throws CreateException;

    /**
     * Edits/Modify the data of the entity passed
     *
     * @param entity
     * @throws UpdateException If the creation method threw an exception
     */
    public void edit(Subject subject) throws UpdateException;

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param entity
     * @throws DeleteException If the creation method threw an exception
     */
    public void remove(Integer id) throws DeleteException;

    /**
     * Finds the entity value using the primary key object passed by parameter
     *
     * @param obj Primary key of the entity
     * @return Returns the entity found by using the primary key, can be NULL
     * @throws ReadException If the read
     */
    public Subject find(Integer id) throws ReadException;
}
