/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.interfaces.CourseEJBLocal;
import entities.Course;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.List;

/**
 *
 * @author Joritz
 */
public class CourseEJB implements CourseEJBLocal {

    @Override
    public void create(Course entity) throws CreateException {
        
    }

    @Override
    public void edit(Course entity) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Course entity) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course find(Object obj) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> findAll() throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course findByName(Course entity) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course findByDate(Course entity) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
