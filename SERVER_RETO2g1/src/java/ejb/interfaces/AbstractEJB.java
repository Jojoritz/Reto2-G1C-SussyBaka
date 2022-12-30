/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.interfaces;

import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This generic interface declares basic {@code CUD} contract to improve
 * boilerplate code:
 * <li>{@link #create Create}</li>
 * <li>{@link #edit Edit}</li>
 * <li>{@link #remove Remove}</li>
 *
 * @author yeguo
 * @param <T> Generic type
 */
@Local
public abstract class AbstractEJB<T> {

    private Class<T> entityClass;

    /**
     * Logger for the class.
     */
    private static final Logger LOG
            = Logger.getLogger(AbstractEJB.class.getName());

    @PersistenceContext
    protected EntityManager em;

    /**
     * Creates/inserts the data of the entity passed
     *
     * @param entity
     * @throws CreateException If the creation method threw an exception
     */
    public void create(T entity) throws CreateException {
        try {
            LOG.info(String.format("EJB: Creating %s", entity.getClass().getName()));
            em.persist(entity);
            LOG.info(String.format("EJB: %s created successfully", entity.getClass().getName()));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("EJB: Exception on creating %s, {0}",
                    entity.getClass().getName()), e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Edits/Modify the data of the entity passed
     *
     * @param entity
     * @throws UpdateException If the creation method threw an exception
     */
    public void edit(T entity) throws UpdateException {
        try {
            em.merge(entity);
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }

    }

    /**
     * Deletes/Removes all the data from the entity passed
     *
     * @param entity
     * @throws DeleteException If the creation method threw an exception
     */
    public void remove(T entity) throws DeleteException {
        try {
            entity = em.merge(entity);
            em.remove(entity);
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Finds a
     *
     * @param obj Primary key of the entity
     * @return Returns the entity found by using the primary key, can be NULL
     * @throws ReadException If the read
     */
    public T find(Object obj) throws ReadException {
        T entity;
        try {
            entity = em.find(entityClass, obj);
            return entity;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

}
