package ejb.interfaces;

import exception.*;
import java.util.List;

/**
 * This generic interface declares basic {@code CRUD} contract to improve
 * boilerplate code:
 * <li>{@link #create Create}</li>
 * <li>{@link #edit Edit}</li>
 * <li>{@link #remove Remove}</li>
 * <li>{@link #find Find}</li>
 * <li>{@link #findAll FindAll}</li>
 *
 * @author yeguo
 * @param <T> Generic type
 */
public interface InterfaceEJBCRUD<T> {

    /**
     * Creates/inserts the data of the entity passed
     *
     * @param entity
     * @throws CreateException If the creation method threw an exception
     */
    public void create(T entity) throws CreateException;

    /**
     * Edits/Modify the data of the entity passed
     *
     * @param entity
     * @throws UpdateException If the creation method threw an exception
     */
    public void edit(T entity) throws UpdateException;

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param entity
     * @throws DeleteException If the creation method threw an exception
     */
    public void remove(T entity) throws DeleteException;

    /**
     * Finds a single entity value by using the ID
     *
     * @param obj The ID of the entity to find
     * @return The {@code Entity} object containing the data
     * @throws ReadException
     */
    public T find(Object obj) throws ReadException;

    /**
     * Finds all values from the Entity
     * 
     * @return @throws ReadException
     */
    public List<T> findAll() throws ReadException;

}
