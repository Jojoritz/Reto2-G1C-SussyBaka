/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb.interfaces;

import server.entities.Course;
import server.exception.ReadException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Local;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.UpdateException;

/**
 *
 * @author Joritz
 */
@Local
public interface CourseEJBLocal{
        
    /**
     * Logger for the class.
     */
    static final Logger LOG
            = Logger.getLogger(AbstractEJB.class.getName());
    
    /**
     * Creates/inserts the data of the entity passed
     *
     * @param entity the course to create
     * @throws CreateException If the creation method threw an exception
     */
    public void create(Course entity) throws CreateException;

    /**
     * Edits/Modify the data of the entity passed
     *
     * @param entity the course to edit
     * @throws UpdateException If the creation method threw an exception
     */
    public void edit(Course entity) throws UpdateException;

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param id the id of the course to delete
     * @throws DeleteException If the creation method threw an exception
     */
    public void remove(Integer id) throws DeleteException;
    
    /**
     * Gets the Course with a specific id
     * 
     * @param id ID to filter by
     * @return Returns the Course with that specific ID
     * @throws ReadException Throws exception if there was any error
     * when getting the Course by id
     */
    public Course find(Integer id) throws ReadException;

    /**
     * Gets all the Courses
     * 
     * @return Returns all the existing Courses in a LIST
     * @throws ReadException Throws exception if there was any error
     * when getting the Courses
     */
    public List<Course> findAll() throws ReadException;
    
    /**
     * Gets the Courses with a specific name
     * 
     * @param name Name to filter by
     * @return Returns a LIST with the Courses with that specific name
     * @throws ReadException Throws exception if there was any error
     * when getting the Courses by name
     */
    public List<Course> findByName(String name) throws ReadException;
    
    /**
     * Gets the Courses with a specific Date
     * 
     * @param startDate Date to filter by
     * @return Returns a LIST with the Courses with that specific Date
     * @throws ReadException Throws exception if there was any error
     * when getting the Courses by date
     */
    public List<Course> findByDate(Date startDate) throws ReadException;
}

