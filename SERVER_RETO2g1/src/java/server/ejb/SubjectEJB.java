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
import server.exception.ReadException;
import java.util.logging.Logger;
import java.util.Set;
import javax.ejb.Stateless;

/**
 *
 * @author ioritz the Subject EJB
 *
 */
@Stateless(name = "SubjectEJB")
public class SubjectEJB extends SubjectEJBLocal {

    private static final Logger LOGGER = Logger.getLogger(SubjectEJB.class.getName());
    
    @Override
    public Subject searchByName(String name) throws ReadException {
        Subject subject = null;
        try {
            
            //Getting the subject collection by name
            LOGGER.info("Searching the subject by name");
            subject = (Subject) em.createNamedQuery("getSubjectByName").
                    setParameter("name", name).getSingleResult();

        } catch (Exception e) {
            LOGGER.severe("An error ocurred when searching the subject");
            throw new ReadException("An error ocurred when searching the subject");
            
        }
        return subject;
    }

    @Override
    public Set<Subject> searchByType(String type) throws ReadException {
        Set<Subject> subjects = null;
        try {
            LOGGER.info("Searching the subject by type");
            //Getting the subject collection by type
            subjects = (Set<Subject>) em.createNamedQuery("getSubjectsByType")
                    .setParameter("type", type).getResultList();
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when searching the subject");
            throw new ReadException("An error ocurred when searching the subject");
        }
        return subjects;
    }

    @Override
    public Set<Subject> searchByLevel(String level) throws ReadException {
        Set<Subject> subjects = null;
        try {
            LOGGER.info("Searching the subject by level");
            //Getting the subject collection by level
            subjects = (Set<Subject>) em.createNamedQuery("getSubjectsByLevel")
                    .setParameter("level", level).getResultList();
        } catch (Exception e) {
            LOGGER.severe("An error ocurred when searching the subject");
            throw new ReadException("An error ocurred when searching the subject");
        }
        return subjects;
    }

    @Override
    public Subject getSubjectRelationshipsData(Subject subject) throws ReadException {
        try {
            LOGGER.info("Searching the data of the relationships of the subject with other entityes");
            //Getting the course with subject relation
            LOGGER.info("Searching the data of the course and subject relationship");
            Set<Course> coursesWithSubject = (Set<Course>) em.createNamedQuery("getSubjectCourseRelationship")
                    .setParameter("subjectId", subject.getSubjectId()).getResultList();
            LOGGER.info("Searching the data of the subject and teachers relationship");
            Set<Teacher> teachersSpecialized = (Set<Teacher>) em.createNamedQuery("getSubjectTeacherRelationship")
                    .setParameter("subjectId", subject.getSubjectId()).getResultList();
            
            //Setting the relations in the entity
            subject.setCourseWithSubject(coursesWithSubject);
            subject.setTeachersSpecializedInSubject(teachersSpecialized);
        } catch (Exception e) {
            throw new ReadException();
        }
        return subject;
    }


}
