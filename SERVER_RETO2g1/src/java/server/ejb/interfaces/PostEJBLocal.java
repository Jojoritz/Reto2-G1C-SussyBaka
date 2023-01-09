/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb.interfaces;

import server.entities.Course;
import server.entities.Post;
import server.exception.ReadException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.UpdateException;

/**
 * This {@code Abstract class} extends the {@link AbstractEJB} to implement
 * necessary methods for the entity {@link Post}
 *
 *
 *
 * @author yeguo
 */
@Local
public interface PostEJBLocal {

    /**
     * Gets all the post from a specific date
     *
     * @param course The entity course on where to get the post
     * @param date Date to filter by
     * @return Returns a SET with all the post founded by a specific date
     * @throws ReadException Throws exception if there was any error when
     * getting the posts by date
     */
    public List<Post> getPostByDate(Course course, Date date) throws ReadException;

    /**
     * Gets all the post from a range of dates
     *
     * @param course The entity course on where to get the post
     * @param startDate Start date
     * @param endDate End date
     * @return Returns a SET with all the post founded by range of dates
     * @throws ReadException Throws exception if there was any error when
     * getting the posts by date range
     */
    public List<Post> getPostByDateRange(Course course, Date startDate, Date endDate) throws ReadException;

    /**
     * Gets all the posts that contains a specific title name
     *
     * @param course The entity course on where to get the post
     * @param title The title string to use as filter
     * @return Returns a SET with all the post founded filtering by
     * @throws ReadException Throws exception if there was any error when
     * getting the posts by the post title
     */
    public List<Post> getPostByTitle(Course course, String title) throws ReadException;

    
    /**
     * Creates/inserts the data of the entity passed
     *
     * @param entity
     * @throws CreateException If the creation method threw an exception
     */
    public void create(Post entity) throws CreateException;

    /**
     * Edits/Modify the data of the entity passed
     *
     * @param entity
     * @throws UpdateException If the creation method threw an exception
     */
    public void edit(Post entity) throws UpdateException;

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param entity
     * @throws DeleteException If the creation method threw an exception
     */
    public void remove(Post entity) throws DeleteException;

    /**
     * Finds the entity value using the primary key object passed by parameter
     *
     * @param obj Primary key of the entity
     * @return Returns the entity found by using the primary key, can be NULL
     * @throws ReadException If the read
     */
    public Post find(Object obj) throws ReadException;
}
