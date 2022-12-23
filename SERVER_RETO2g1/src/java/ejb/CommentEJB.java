/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.interfaces.CommentEJBLocal;
import entities.Comment;
import entities.Post;
import exception.ReadException;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;

/**
 *
 * @author yeguo
 */
@Stateless
public class CommentEJB extends CommentEJBLocal {

    @Override
    public Comment find(Object obj) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> findAll() throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
