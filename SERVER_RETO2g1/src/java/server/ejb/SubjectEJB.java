/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.SubjectEJBLocal;
import server.entities.Course;
import server.entities.Subject;
import server.entities.Teacher;
import javax.ejb.Stateless;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.ReadException;
import server.exception.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ioritz the Subject EJB
 *
 */
@Stateless
public class SubjectEJB implements SubjectEJBLocal {

    private static final Logger LOGGER = Logger.getLogger(SubjectEJB.class.getName());
    @PersistenceContext(unitName="JavaFX-WebApplicationUD5ExamplePU")
    private EntityManager em;

    @Override
    public List<Subject> searchByName(String name) throws ReadException {
        List<Subject> subjects = null;
        try {

            //Getting the subject collection by name
            LOGGER.info("Searching the subject by name");
            subjects =  em.createNamedQuery("getSubjectByName").
                    setParameter("name", name).getResultList();

        } catch (Exception e) {
            LOGGER.severe("An error ocurred when searching the subject");
            throw new ReadException("An error ocurred when searching the subject");

        }
        return subjects;
    }

    @Override
    public List<Subject> searchByType(String type) throws ReadException {
        List<Subject> subjects = null;
        try {
            LOGGER.info("Searching the subject by type");
            //Getting the subject collection by type
            subjects =  em.createNamedQuery("getSubjectsByType")
                    .setParameter("type", type).getResultList();
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when searching the subject");
            throw new ReadException("An error ocurred when searching the subject");
        }
        return subjects;
    }

    @Override
    public List<Subject> searchByLevel(String level) throws ReadException {
        List<Subject> subjects = null;
        try {
            LOGGER.info("Searching the subject by level");
            //Getting the subject collection by level
            subjects = em.createNamedQuery("getSubjectsByLevel")
                    .setParameter("level", level).getResultList();
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when searching the subject");
            throw new ReadException("An error ocurred when searching the subject");
        }
        return subjects;
    }

    

    @Override
    public List<Subject> findAll() throws ReadException {
        List<Subject> subjects = null;

        try {
            LOGGER.info("Searching the subjects");
            subjects =  em.createNamedQuery("findAllSubjects").getResultList();
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when searching the subjecet");
            throw new ReadException("An error ocurred when searching the subjects");
        }
        return subjects;
    }

    @Override
    public void create(Subject subject) throws CreateException {
        try {
            LOGGER.info(String.format("EJB: Creating %s", subject.getClass().getName()));
            if (!em.contains(subject.getTeachersSpecializedInSubject()) && subject.getTeachersSpecializedInSubject() != null) {
                 subject.setTeachersSpecializedInSubject(em.merge(subject.getTeachersSpecializedInSubject()));
            }
            
            em.persist(subject);
            LOGGER.info(String.format("EJB: %s created successfully", subject.getClass().getName()));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, String.format("EJB: Exception on creating %s, {0}",
                    subject.getClass().getName()), e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void edit(Subject subject) throws UpdateException {
        try {
            if (!em.contains(subject.getTeachersSpecializedInSubject()) && subject.getTeachersSpecializedInSubject() != null) {
                 subject.setTeachersSpecializedInSubject(em.merge(subject.getTeachersSpecializedInSubject()));
            }
            em.merge(subject);
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void remove(Integer id) throws DeleteException {
        try {
            Subject subject = find(id);
            subject = em.merge(subject);
            em.remove(subject);
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public Subject find(Integer id) throws ReadException {
        Subject subject;
        try {
            subject = em.find(Subject.class, id);
            return subject;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

}
