/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import entities.Comment;
import entities.Post;
import exception.ReadException;
import java.util.List;

/**
 * This {@code Abstract class} extends the {@link AbstractEJB} to implement
 * necessary methods for the entity {@link Post}
 *
 *
 *
 * @author yeguo
 */
public abstract class CommentEJBLocal extends AbstractEJB<Comment> {

    /**
     * Gets the comments that the post has
     *
     * @param id The ID of the post from where to get the comments {@link Post}
     * @return Returns a List with all the comments from the post
     * @throws exception.ReadException Throws exception if there was any error
     * when getting the comments
     */
    public abstract List<Comment> getComments(Integer id) throws ReadException;

}
