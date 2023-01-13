/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.CommentEJBLocal;
import server.entities.Comment;
import server.exception.ReadException;
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
 * @author Henri
 */
@Stateless
public class CommentEJB implements CommentEJBLocal {

    /**
     * Logger for the class.
     */
    protected static final Logger LOG
            = Logger.getLogger(CommentEJB.class.getName());

    @PersistenceContext(unitName = "JavaFX-WebApplicationUD5ExamplePU")
    protected EntityManager em;

    @Override
    public List<Comment> getComments(Integer id) throws ReadException {
        try {
            List<Comment> comments;
            LOG.info("CommentEJB: Getting all comments...");
            comments = em.createNamedQuery("getCommentsByPostID").
                    setParameter("idPost", id).getResultList();
            return comments;
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
    public void create(Comment entity) throws CreateException {
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
    public void edit(Comment entity) throws UpdateException {
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
    public void remove(Comment entity) throws DeleteException {
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
    public Comment find(Integer obj) throws ReadException {
        Comment entity;
        try {
            entity = em.find(Comment.class, obj);
            return entity;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    

}
