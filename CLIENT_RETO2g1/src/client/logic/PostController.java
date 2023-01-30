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
public interface PostController {

    /**
     *
     * @param <T>
     * @param responseType
     * @param courseId
     * @param date
     * @return
     * @throws client.logic.exception.BusinessLogicException
     */
    public <T> T findByDate(GenericType<T> responseType, String courseId, String date) throws BusinessLogicException;

    /**
     *
     * @param <T>
     * @param responseType
     * @param courseId
     * @param from
     * @param to
     * @return
     * @throws client.logic.exception.BusinessLogicException
     */
    public <T> T findByDateRange(GenericType<T> responseType, String courseId, String from, String to) throws BusinessLogicException;

    /**
     *
     * @param <T>
     * @param responseType
     * @param id
     * @return
     * @throws client.logic.exception.BusinessLogicException
     */
    public <T> T find(GenericType<T> responseType, String id) throws BusinessLogicException;

    /**
     *
     * @param requestEntity
     * @throws client.logic.exception.BusinessLogicException
     */
    public void create(Object requestEntity) throws BusinessLogicException;

    /**
     *
     * @param requestEntity
     * @throws client.logic.exception.BusinessLogicException
     */
    public void edit(Object requestEntity) throws BusinessLogicException;

    /**
     *
     * @param <T>
     * @param responseType
     * @param title
     * @param courseId
     * @return
     * @throws client.logic.exception.BusinessLogicException
     */
    public <T> T findByTitle(GenericType<T> responseType, String courseId, String title) throws BusinessLogicException;

    /**
     *
     * @param id
     * @throws client.logic.exception.BusinessLogicException
     */
    public void remove(String id) throws BusinessLogicException;

    /**
     *
     * @param <T>
     * @param responseType
     * @param courseId
     * @return
     * @throws client.logic.exception.BusinessLogicException
     */
    public <T> T getCoursePosts(GenericType<T> responseType, String courseId) throws BusinessLogicException;

    /**
     *
     */
    public void close();

}
