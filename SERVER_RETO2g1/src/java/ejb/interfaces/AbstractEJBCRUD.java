package ejb.interfaces;

import java.util.List;

/**
 * This generic interface creates basic {@code CRUD} contract:
 * <li>{@link #create Create}</li>
 * <li>{@link #edit Edit}</li>
 * <li>{@link #remove Remove}</li>
 * <li>{@link #find Find}</li>
 * <li>{@link #findAll FindAll}</li>
 *
 * @author yeguo
 * @param <T> Generic type
 */
public interface AbstractEJBCRUD<T> {

    public void create(T entity);

    public void edit(T entity);

    public void remove(T entity);

    public T find(Object id);

    public List<T> findAll();

}
