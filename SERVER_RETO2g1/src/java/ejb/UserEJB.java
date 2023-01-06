/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.interfaces.UserEJBLocal;
import entities.Course;
import entities.Student;
import entities.Subject;
import entities.Teacher;
import entities.User;
import entities.enumerations.UserPrivilege;
import exception.CreateException;
import exception.ReadException;
import java.security.SecureRandom;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author ioritz
 */
@Stateless(name="UserEJB")
public class UserEJB extends UserEJBLocal {

    private static final Logger LOGGER = Logger.getLogger(UserEJB.class.getName());
    
    @Override
    public User getUserRelationshipsData(User user) throws ReadException {
        try {
            LOGGER.info("Starting getting the relationships of the user");
            // a condition where if the user is a teacher executes one named query
            //and if it is a student another to obtain corresponding relationships data
            if (user.getPrivilege().equals(UserPrivilege.STUDENT)) {
                LOGGER.info("Getting the student relationships of the student");
                //Obtaining the studying courses of the student and saving in a collection to latter set in the user studying courses collection
                Set<Course> studyingCourses = (Set<Course>) em.createNamedQuery("getStudentCourseData")
                        .setParameter("id", user.getId()).getResultList();

                ((Student) user).setStudyingCourses(studyingCourses);
            } else if (user.getPrivilege().equals(UserPrivilege.TEACHER)) {
                LOGGER.info("Starting getting the relationships of the teacher");
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
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }

        return user;
    }
    @Override
    public void create(User entity) throws CreateException{
        
        try {
            User findedUser = find(entity);
            if (findedUser != null) {
                throw new Exception("The user all ready exist");
            }
            LOGGER.info(String.format("EJB: Creating %s", entity.getClass().getName()));
            entity = hashEntityPassword(entity);
            em.persist(entity);
            LOGGER.info(String.format("EJB: %s created successfully", entity.getClass().getName()));
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
    @Override
    public User find(Object obj) throws ReadException{
        try {
            User passedUser = (User) obj;
            User user = (User) em.createNamedQuery("getUserLogin").setParameter("login", passedUser.getLogin())
                    .setParameter("password", passedUser.getPassword()).getSingleResult();
            return user;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * The method to hash the password of the user
     * @param entity The user with thte password without hashed
     * @return the user with the hashed password
     */
    private User hashEntityPassword(User entity) {
        //TODO do the method to hash the password. Look at the exercise done in 
        //class and, javadoc and the chatGPT

        //Contraseña a hashear
       /* String password = entity.getPassword();
        
        //Generando la salt
        SecureRandom random = SecureRandom.getInstance("")
        byte[] salt = 
        */
        
        return entity;
    }
    
}
