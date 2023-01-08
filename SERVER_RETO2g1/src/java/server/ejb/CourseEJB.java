/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.CourseEJBLocal;
import server.entities.Course;
import server.exception.ReadException;
import java.util.List;

/**
 *
 * @author Joritz
 */
public class CourseEJB extends CourseEJBLocal {

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
