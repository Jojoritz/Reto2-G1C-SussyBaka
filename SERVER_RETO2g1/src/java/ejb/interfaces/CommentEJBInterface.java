package ejb.interfaces;

import javax.ejb.Local;

import entities.Comment;

/**
 *
 * @author yeguo
 */
@Local
public interface CommentEJBInterface extends AbstractEJBCRUD<Comment>{

}
