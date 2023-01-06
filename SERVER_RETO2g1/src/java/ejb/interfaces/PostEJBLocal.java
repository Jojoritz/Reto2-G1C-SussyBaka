/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import entities.Course;
import entities.Post;
import exception.ReadException;
import java.util.Date;
import java.util.List;

/**
 * This {@code Abstract class} extends the {@link AbstractEJB} to implement
 * necessary methods for the entity {@link Post}
 *
 *
 *
 * @author yeguo
 */
public abstract class PostEJBLocal extends AbstractEJB<Post> {

    /**
     * Gets all the post from a specific date
     *
     * @param course The entity course on where to get the post
     * @param date Date to filter by
     * @return Returns a SET with all the post founded by a specific date
     * @throws exception.ReadException Throws exception if there was any error
     * when getting the posts by date
     */
    public abstract List<Post> getPostByDate(Course course, Date date) throws ReadException;

    /**
     * Gets all the post from a range of dates
     *
     * @param course The entity course on where to get the post
     * @param startDate Start date
     * @param endDate End date
     * @return Returns a SET with all the post founded by range of dates
     * @throws exception.ReadException Throws exception if there was any error
     * when getting the posts by date range
     */
    public abstract List<Post> getPostByDateRange(Course course, Date startDate, Date endDate) throws ReadException;

    /**
     * Gets all the posts that contains a specific title name
     *
     * @param course The entity course on where to get the post
     * @param title The title string to use as filter
     * @return Returns a SET with all the post founded filtering by
     * @throws exception.ReadException Throws exception if there was any error
     * when getting the posts by the post title
     */
    public abstract List<Post> getPostByTitle(Course course, String title) throws ReadException;

}
