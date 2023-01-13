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
import server.entities.dto.CommentDTO;
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
    
    @PersistenceContext
    protected EntityManager em;
    
    @Override
    public List<Comment> getComments(Integer id) throws ReadException {
        try {
            List<Comment> comments;
            LOG.info("CommentEJB: Getting all comments...");
            comments = CommentDTO.convertDTOsComment(em.createNamedQuery("getCommentsByPostID", CommentDTO.class).
                    setParameter("postId", id).getResultList());
            return comments;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ReadException(e.getMessage());
        }
        
    }
    
    @Override
    public void create(Comment entity) throws CreateException {
        try {
            LOG.info(String.format("CommentEJB: Creating %s", Comment.class.getName()));
            em.persist(entity);
            LOG.info(String.format("CommentEJB: %s created successfully", Comment.class.getName()));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("EJB: Exception on creating %s, {0}",
                    entity.getClass().getName()), e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
    
    @Override
    public void edit(Comment entity) throws UpdateException {
        try {
            LOG.info(String.format("CommentEJB: Editing %s", Comment.class.getName()));
            em.merge(entity);
            em.flush();
            LOG.info(String.format("CommentEJB: %s edited successfully", Comment.class.getName()));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("EJB: Exception on updating %s, {0}",
                    entity.getClass().getName()), e.getMessage());
            throw new UpdateException(e.getMessage());
        }
        
    }
    
    @Override
    public void remove(Integer id) throws DeleteException {
        try {
            LOG.info(String.format("CommentEJB: Deleting %s", Comment.class.getName()));
            //Comment comment = find(id);
            Comment comment = em.find(Comment.class, id);

            comment = em.merge(comment);
            em.remove(comment);
            LOG.info(String.format("CommentEJB: %s deleted successfully", Comment.class.getName()));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("EJB: Exception on deleting %s, {0}",
                    Comment.class.getName()), e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
    
    @Override
    public Comment find(Integer obj) throws ReadException {
        Comment entity;
        try {
            LOG.info(String.format("CommentEJB: Searching %s with id %d", Comment.class.getName(), obj));
            entity = em.createNamedQuery("getCommentsByID", Comment.class).
                    setParameter("commentId", obj).getSingleResult();
            return entity;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("EJB: Exception on reading %s, {0}",
                    Comment.class.getName()), e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }
    
}
