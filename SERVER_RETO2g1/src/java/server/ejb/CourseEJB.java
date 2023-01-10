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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.UpdateException;

/**
 *
 * @author Joritz
 */
@Stateless
public class CourseEJB implements CourseEJBLocal {

    private static final Logger LOG = Logger.getLogger("ejb.CourseEJB");
    @PersistenceContext
    EntityManager em;

    @Override
    public void create(Course entity) throws CreateException {
        try {
            LOG.info(String.format("EJB: Creating %s", entity.getClass().getName()));
            em.persist(entity);
            LOG.info(String.format("EJB: %s created successfully", entity.getClass().getName()));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("EJB: Exception on creating %s, {0}",
                    entity.getClass().getName()), e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void edit(Course entity) throws UpdateException {
        try {
            em.merge(entity);
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }

    }

    @Override
    public void remove(Course entity) throws DeleteException {
        try {
            entity = em.merge(entity);
            em.remove(entity);
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public Course find(Integer id) throws ReadException {
        Course entity;
        try {
            entity = em.find(Course.class, id);
            return entity;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

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
