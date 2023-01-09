/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.PostEJBLocal;
import server.entities.Course;
import server.entities.Post;
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
 * @author yeguo
 */
@Stateless
public class PostEJB implements PostEJBLocal {

    /**
     * Logger for the class.
     */
    protected static final Logger LOG
            = Logger.getLogger(PostEJB.class.getName());

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<Post> getPostByDate(Course course, Date date) throws ReadException {
        try {
            List<Post> posts;
            LOG.info("PostEJB: Get all post from a specify date...");
            posts = em.createNamedQuery("findByDate").
                    setParameter("date", date).getResultList();
            return posts;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    @Override
    public List<Post> getPostByDateRange(Course course, Date startDate, Date endDate) throws ReadException {
        try {
            List<Post> posts;
            LOG.info("PostEJB: Get all post by date range...");
            posts = em.createNamedQuery("findByDateRange")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate).getResultList();
            return posts;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    @Override
    public List<Post> getPostByTitle(Course course, String title) throws ReadException {
        try {
            List<Post> posts;
            LOG.info("PostEJB: Get all post by title...");
            posts = em.createNamedQuery("findByTitle").
                    setParameter("title", title).getResultList();
            return posts;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Creates/inserts the data of the entity passed
     *
     * @param entity
     * @throws CreateException If the creation method threw an exception
     */
    @Override
    public void create(Post entity) throws CreateException {
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

    /**
     * Edits/Modify the data of the entity passed
     *
     * @param entity
     * @throws UpdateException If the creation method threw an exception
     */
    @Override
    public void edit(Post entity) throws UpdateException {
        try {
            em.merge(entity);
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }

    }

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param entity
     * @throws DeleteException If the creation method threw an exception
     */
    @Override
    public void remove(Post entity) throws DeleteException {
        try {
            entity = em.merge(entity);
            em.remove(entity);
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Finds the entity value using the primary key object passed by parameter
     *
     * @param obj Primary key of the entity
     * @return Returns the entity found by using the primary key, can be NULL
     * @throws ReadException If the read
     */
    @Override
    public Post find(Integer obj) throws ReadException {
        Post entity;
        try {
            LOG.info("hola");
            entity = em.find(Post.class, obj);
            LOG.info(entity.toString());
            return entity;
        } catch (Exception e) {
            //LOG.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

}
