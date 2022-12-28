/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.interfaces.SubjectEJBLocal;
import entities.Course;
import entities.Subject;
import entities.Teacher;
import exception.ReadException;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author ioritz the Subject EJB
 *
 */
@Stateless
public class SubjectEJB extends SubjectEJBLocal {

    @Override
    public Subject searchByName(Subject obj) throws ReadException {
        try {
            //Getting the subject collection by name
            obj = (Subject) em.createNamedQuery("getSubjectByName").
                    setParameter("name", obj.getName()).getSingleResult();

        } catch (Exception e) {
            throw new ReadException();
        }
        return obj;
    }

    @Override
    public Set<Subject> searchByType(Subject obj) throws ReadException {
        Set<Subject> subjects = null;
        try {
            //Getting the subject collection by type
            subjects = (Set<Subject>) em.createNamedQuery("getSubjectsByType")
                    .setParameter("type", obj.getType()).getResultList();
        } catch (Exception e) {
            throw new ReadException();
        }
        return subjects;
    }

    @Override
    public Set<Subject> searchByLevel(Subject obj) throws ReadException {
        Set<Subject> subjects = null;
        try {
            //Getting the subject collection by level
            subjects = (Set<Subject>) em.createNamedQuery("getSubjectsByLevel")
                    .setParameter("level", obj.getLevel()).getResultList();
        } catch (Exception e) {
            throw new ReadException();
        }
        return subjects;
    }

    @Override
    public Subject getSubjectRelationshipsData(Subject obj) throws ReadException {
        try {
            //Getting the course with subject relation
            Set<Course> coursesWithSubject = (Set<Course>) em.createNamedQuery("getSubjectCourseRelationship")
                    .setParameter("subjectId", obj.getSubjectId()).getResultList();
            
            Set<Teacher> teachersSpecialized = (Set<Teacher>) em.createNamedQuery("getSubjectTeacherRelationship")
                    .setParameter("subjectId", obj.getSubjectId()).getResultList();
            
            //Setting the relations in the entity
            obj.setCourseWithSubject(coursesWithSubject);
            obj.setTeachersSpecializedInSubject(teachersSpecialized);
        } catch (Exception e) {
            throw new ReadException();
        }
        return obj;
    }

    @Override
    public Subject find(Object obj) throws ReadException {
        Subject subject;
        try {
            subject = em.find(Subject.class, obj);
        } catch (Exception e) {
            throw new ReadException();
        }
        return subject;
    }

    @Override
    public List<Subject> findAll() throws ReadException {
        List<Subject> subjects = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subject.class));
            subjects = em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new ReadException();
        }

        return subjects;
    }

}
