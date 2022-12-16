package ejb.interfaces;

import entities.Post;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yeguo
 */
@Local
public interface PostEJBInterface extends AbstractEJBCRUD<Post> {

    public List<Post> filterBy(Object o, FilterBy param);

}
