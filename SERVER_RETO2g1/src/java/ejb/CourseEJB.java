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
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joritz
 */
public class CourseEJB implements CourseEJBLocal {

    private static final Logger LOG = Logger.getLogger("ejb.CourseEJB");
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Course entity) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        Course course = new Course();
        try {
            course = (Course) em.createNamedQuery("findCourse").setParameter("courseId", 1);
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return course;
    }

    @Override
    public List<Course> findAll() throws ReadException {
        List<Course> courses = null;
        try {
            courses = em.createNamedQuery("findAllCourses").getResultList();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return courses;
    }

    @Override
    public Course findByName(Course entity) throws ReadException {
        Course course = new Course();
        try {
            course = (Course) em.createNamedQuery("findCourseByName").setParameter("name", "Gerardo");
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return course;
    }

    @Override
    public Course findByDate(Course entity) throws ReadException {
        Course course = new Course();
        try {
            course = (Course) em.createNamedQuery("findCourseByDate").setParameter("startDate", "23/12/2022");
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return course;
    }

}
