/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.PostEJBLocal;
import server.entities.Post;
import server.exception.ReadException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.entities.Course;
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

    @PersistenceContext(unitName = "JavaFX-WebApplicationUD5ExamplePU")
    protected EntityManager em;

    @Override
    public List<Post> getCoursePosts(Integer courseId) throws ReadException {
        try {
            List<Post> posts;
            LOG.info(String.format("PostEJB: Get all post from course with ID %d", courseId));
            posts = em.createNamedQuery("getCoursePosts").
                    setParameter("courseId", courseId).getResultList();
            return posts;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("PostEJB: Exception on reading %s on courseId: %d",
                    Post.class.getName(), courseId), e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    @Override
    public List<Post> getPostByDate(Integer courseId, Date date) throws ReadException {
        try {
            List<Post> posts;
            LOG.info(String.format("PostEJB: Get all post from date %s", date.toString()));
            posts = em.createNamedQuery("findPostByDate").
                    setParameter("date", date).
                    setParameter("courseId", courseId).getResultList();
            return posts;

        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("PostEJB: Exception on reading %s on courseId: %d and date: %s",
                    Post.class
                            .getName(), courseId, date.toString()), e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    @Override
    public List<Post> getPostByDateRange(Integer courseId, Date startDate, Date endDate) throws ReadException {
        try {
            List<Post> posts;
            LOG.info(String.format("PostEJB: Get all post by date range %s to %s", startDate.toGMTString(), endDate.toString()));
            posts = em.createNamedQuery("findPostByDateRange")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate).
                    setParameter("courseId", courseId).getResultList();
            return posts;

        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("PostEJB: Exception on reading %s on courseId: %d, starting date: %s with ending date: %s",
                    Post.class
                            .getName(), courseId, startDate.toString(), endDate.toString()), e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    @Override
    public List<Post> getPostByTitle(Integer courseId, String title) throws ReadException {
        try {
            List<Post> posts;
            LOG.info(String.format("PostEJB: Get post by title: %s on course with ID: %d", title, courseId));
            title = "%" + title + "%";
            posts = em.createNamedQuery("findPostByTitle").
                    setParameter("title", title).
                    setParameter("courseId", courseId).getResultList();
            return posts;

        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("PostEJB: Exception on reading %s on courseId: %d with title: %s",
                    Post.class
                            .getName(), courseId, title), e.getMessage());
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
            LOG.info(String.format("PostEJB: Creating %s", Post.class.getName()));

            if (!em.contains(entity)) {
                entity.setCourse(em.find(Course.class, entity.getCourse().getCourseId()));
            }
            em.persist(entity);
            LOG.info(String.format("PostEJB: %s created successfully", Post.class.getName()));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("PostEJB: Exception on creating %s",
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
            LOG.info(String.format("PostEJB: editing %s with ID: %d", Post.class
                    .getName(), entity.getPostId()));
            if (!em.contains(entity.getCourse())) {
                entity.setCourse(em.find(Course.class, entity.getCourse().getCourseId()));
            }
            em.merge(entity);
            em.flush();
            LOG.info(String.format("PostEJB: %s edited successfully", Post.class.getName()));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("PostEJB: Exception on editing %s",
                    entity.getClass().getName()), e.getMessage());
            throw new UpdateException(e.getMessage());
        }

    }

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param id ID of the post to remove
     * @throws DeleteException If the creation method threw an exception
     */
    @Override
    public void remove(Integer id) throws DeleteException {
        try {
            LOG.info(String.format("PostEJB: Deleting %s with ID: %d", Post.class
                    .getName(), id));
            Post post = find(id);
            post = em.merge(post);
            em.remove(post);
            LOG.info(String.format("PostEJB: %s deleted successfully", Post.class
                    .getName()));

        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("PostEJB: Exception on deleting %s",
                    Post.class
                            .getName()), e.getMessage());
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
    public Post find(Object obj) throws ReadException {
        Post entity;

        try {
            LOG.info(String.format("PostEJB: Finding %s by ID %s", Post.class
                    .getName(), obj.toString()));
            entity
                    = em.find(Post.class,
                            (Integer) obj);
            return entity;

        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("PostEJB: Exception on reading %s with ID %s",
                    Post.class
                            .getName(), obj.toString()), e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

}
