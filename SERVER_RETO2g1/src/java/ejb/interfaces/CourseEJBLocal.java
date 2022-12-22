/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import entities.Course;
import exception.ReadException;

/**
 *
 * @author Joritz
 */
public interface CourseEJBLocal extends InterfaceEJBCRUD<Course>{
    
    public Course findByName(Course entity) throws ReadException;
    
    public Course findByDate(Course entity) throws ReadException;
}
