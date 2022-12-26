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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yeguo
 * @param <T> Object type
 */
public abstract class AbstractEJB<T> implements InterfaceEJBCRUD<T> {

    /**
     * Logger for the class.
     */
    private static final Logger LOG
            = Logger.getLogger("ejb.interfaces.AbstractEJB");

    @PersistenceContext
    protected EntityManager em;

    @Override
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

    @Override
    public void edit(T entity) throws UpdateException {
        try {
            em.merge(entity);
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }

    }

    @Override
    public void remove(T entity) throws DeleteException {
        try {
            entity = em.merge(entity);
            em.remove(entity);
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public abstract T find(Object obj) throws ReadException;

    @Override
    public abstract List<T> findAll() throws ReadException;

}
