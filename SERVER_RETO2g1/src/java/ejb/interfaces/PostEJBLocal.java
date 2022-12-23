/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import entities.Comment;
import entities.Course;
import entities.Post;
import java.util.Date;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author yeguo
 */
@Local
public abstract class PostEJBLocal extends AbstractEJB<Post> {

    /**
     * Gets the comments that the post has
     *
     * @param post Passes the object {@link Post}
     * @return Returns a SET with all the comments from the post
     */
    public abstract Set<Comment> getComments(Post post);

    /**
     * Gets all the post from a specific date
     *
     * @param course The entity course on where to get the post
     * @param date Date to filter by
     * @return Returns a SET with all the post founded by a specific date
     */
    public abstract Set<Post> getPostByDate(Course course, Date date);

    /**
     * Gets all the post from a range of dates
     *
     * @param course The entity course on where to get the post
     * @return Returns a SET with all the post founded by range of dates
     */
    public abstract Set<Post> getPostByDateRange(Course course);

}
