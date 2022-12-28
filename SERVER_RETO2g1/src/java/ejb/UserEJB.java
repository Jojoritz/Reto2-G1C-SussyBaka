/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.interfaces.UserEJBLocal;
import entities.Course;
import static entities.Course_.teacher;
import entities.Student;
import entities.Subject;
import entities.Teacher;
import entities.User;
import entities.enumerations.UserPrivilege;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author ioritz
 */
@Stateless
public class UserEJB extends UserEJBLocal {

    @Override
    public User getUserRelationshipsData(User user) throws ReadException {
        try {

            // a condition where if the user is a teacher executes one named query
            //and if it is a student another to obtain corresponding relationships data
            if (user.getPrivilege().equals(UserPrivilege.STUDENT)) {
                //Obtaining the studying courses of the student and saving in a collection to latter set in the user studying courses collection
                Set<Course> studyingCourses = (Set<Course>) em.createNamedQuery("getStudentCourseData")
                        .setParameter("id", user.getId()).getResultList();

                ((Student) user).setStudyingCourses(studyingCourses);
            } else if (user.getPrivilege().equals(UserPrivilege.TEACHER)) {
                //Obtaining the teaching courses of the teacher and saving in a collection
                Set<Course> teachingCourses = (Set<Course>) em.createNamedQuery("getTeacherCourseData")
                        .setParameter("id", user.getId()).getResultList();
                //Obtaining the specialized subject of the teacher and saving in a collection
                Set<Subject> teacherSpecializedSubjects = (Set<Subject>) em.createNamedQuery("getTeacherSubjectData")
                        .setParameter("id", user.getId()).getResultList();
                
                //Setting the obtained data to the teacher
                ((Teacher) user).setTeachingCourses(teachingCourses);
                ((Teacher) user).setSpecializedSubjects(teacherSpecializedSubjects);

            }
        } catch (Exception e) {
            throw new ReadException();
        }

        return user;
    }

    @Override
    public User find(Object obj) throws ReadException {
        User user;
        try {
            user = em.find(User.class, obj);
        } catch (Exception e) {
            throw new ReadException();
        }
        return user;
    }

    @Override
    public List<User> findAll() throws ReadException {
        List<User> users = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            users = em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new ReadException();
        }

        return users;
    }

}
