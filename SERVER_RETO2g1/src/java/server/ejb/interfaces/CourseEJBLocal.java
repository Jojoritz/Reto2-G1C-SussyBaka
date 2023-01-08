/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb.interfaces;

import server.entities.Course;
import server.exception.ReadException;

/**
 *
 * @author Joritz
 */
public abstract class CourseEJBLocal extends AbstractEJB<Course>{
    
    public abstract Course findByName(Course entity) throws ReadException;
    
    public abstract Course findByDate(Course entity) throws ReadException;
}
