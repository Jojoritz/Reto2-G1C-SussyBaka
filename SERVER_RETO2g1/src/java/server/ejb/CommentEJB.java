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
import javax.ejb.Stateless;

/**
 *
 * @author Henri
 */
@Stateless
public class CommentEJB extends CommentEJBLocal {

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

}
