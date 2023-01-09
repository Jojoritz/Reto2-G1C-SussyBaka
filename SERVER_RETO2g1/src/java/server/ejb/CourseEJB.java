/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.CourseEJBLocal;
import server.entities.Course;
import server.exception.ReadException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Joritz
 */
@Stateless
public class CourseEJB extends CourseEJBLocal {

    private static final Logger LOG = Logger.getLogger("ejb.CourseEJB");

    @Override
    public List<Course> findAll() throws ReadException {
        List<Course> courses = null;
        LOG.info("CourseEJB: Getting all Courses...");
        //Getting the collection with Courses
        try {
            courses = (List<Course>) em.createNamedQuery("findAllCourses").getResultList();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return courses;
    }

    @Override
    public Course findById(Integer id) throws ReadException {
        Course course = new Course();
        LOG.info("CourseEJB: Getting Course by id...");
        //Getting the Course by ID
        try {
            course = (Course) em.createNamedQuery("findCourse").setParameter("courseId", id);
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return course;
    }

    @Override
    public List<Course> findByName(String name) throws ReadException {
        List<Course> courses = null;
        LOG.info("CourseEJB: Getting Courses by name...");
        //Getting the Collection of Courses by Name
        try {
            courses = (List<Course>) em.createNamedQuery("findCourseByName").setParameter("name", name);
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new ReadException();
        }
        return courses;
    }

    @Override
    public List<Course> findByDate(Date startDate) throws ReadException {
        List<Course> courses = null;
        LOG.info("CourseEJB: Getting all Courses from a specific date...");
        //Getting the Collection of Courses by Date
        try {
            courses = (List<Course>) em.createNamedQuery("findCourseByDate").setParameter("startDate", startDate);
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new ReadException();
        }
        return courses;
    }
}
