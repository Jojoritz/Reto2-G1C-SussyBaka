/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import client.logic.exception.BusinessLogicException;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Henri
 */
public interface CommentController {

    /**
     *
     * @param requestEntity The entity to edit
     * @throws client.logic.exception.BusinessLogicException
     */
    public void edit(Object requestEntity) throws BusinessLogicException;

    /**
     *
     * @param <T> 
     * @param responseType
     * @param id
     * @return
     * @throws client.logic.exception.BusinessLogicException
     */
    public <T> T find(Class<T> responseType, String id) throws BusinessLogicException;

    /**
     *
     * @param requestEntity
     * @throws client.logic.exception.BusinessLogicException
     */
    public void create(Object requestEntity) throws BusinessLogicException;

    /**
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return
     * @throws client.logic.exception.BusinessLogicException
     */
    public <T> T findAll(GenericType<T> responseType, String id) throws BusinessLogicException;

    /**
     *
     * @param id
     * @throws client.logic.exception.BusinessLogicException
     */
    public void remove(String id) throws BusinessLogicException;

    /**
     *
     */
    public void close();
}
