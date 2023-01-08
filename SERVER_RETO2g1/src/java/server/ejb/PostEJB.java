/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.PostEJBLocal;
import server.entities.Course;
import server.entities.Post;
import server.exception.ReadException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author yeguo
 */
@Stateless
public class PostEJB extends PostEJBLocal {

    @Override
    public List<Post> getPostByDate(Course course, Date date) throws ReadException {
        try {
            List<Post> posts;
            LOG.info("PostEJB: Get all post from a specify date...");
            posts = em.createNamedQuery("findByDate").
                    setParameter("date", date).getResultList();
            return posts;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    @Override
    public List<Post> getPostByDateRange(Course course, Date startDate, Date endDate) throws ReadException {
        try {
            List<Post> posts;
            LOG.info("PostEJB: Get all post by date range...");
            posts = em.createNamedQuery("findByDateRange")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate).getResultList();
            return posts;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    @Override
    public List<Post> getPostByTitle(Course course, String title) throws ReadException {
        try {
            List<Post> posts;
            LOG.info("PostEJB: Get all post by title...");
            posts = em.createNamedQuery("findByTitle").
                    setParameter("title", title).getResultList();
            return posts;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

}
