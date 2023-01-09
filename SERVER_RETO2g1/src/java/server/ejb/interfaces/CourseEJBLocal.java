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
import javax.ejb.Local;

/**
 *
 * @author Joritz
 */
@Local
public abstract class CourseEJBLocal extends AbstractEJB<Course>{
    
    /**
     * Gets the Course with a specific id
     * 
     * @param id ID to filter by
     * @return Returns the Course with that specific ID
     * @throws ReadException Throws exception if there was any error
     * when getting the Course by id
     */
    public abstract Course findById(Integer id) throws ReadException;
    
    /**
     * Gets all the Courses
     * 
     * @return Returns all the existing Courses in a LIST
     * @throws ReadException Throws exception if there was any error
     * when getting the Courses
     */
    public abstract List<Course> findAll() throws ReadException;
    
    /**
     * Gets the Courses with a specific name
     * 
     * @param name Name to filter by
     * @return Returns a LIST with the Courses with that specific name
     * @throws ReadException Throws exception if there was any error
     * when getting the Courses by name
     */
    public abstract List<Course> findByName(String name) throws ReadException;
    
    /**
     * Gets the Courses with a specific Date
     * 
     * @param startDate Date to filter by
     * @return Returns a LIST with the Courses with that specific Date
     * @throws ReadException Throws exception if there was any error
     * when getting the Courses by date
     */
    public abstract List<Course> findByDate(Date startDate) throws ReadException;
}

