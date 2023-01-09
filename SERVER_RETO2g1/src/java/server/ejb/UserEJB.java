/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.UserEJBLocal;
import server.entities.Course;
import server.entities.Student;
import server.entities.Subject;
import server.entities.Teacher;
import server.entities.User;
import server.entities.enumerations.UserPrivilege;
import server.exception.CreateException;
import server.exception.ReadException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.exception.DeleteException;
import server.exception.UpdateException;

/**
 *
 * @author ioritz
 */
@Stateless
public class UserEJB implements UserEJBLocal {

    private static final Logger LOGGER = Logger.getLogger(UserEJB.class.getName());
    @PersistenceContext
    private EntityManager em;
    
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
            LOGGER.info("Searching if the user exist");
            User findedUser = find(entity.getLogin());
            if (findedUser != null) {
                throw new Exception("The user all ready exist");
            }
            LOGGER.info(String.format("EJB: Creating %s", entity.getClass().getName()));
            entity = hashUserPassword(entity);
            em.persist(entity);
            LOGGER.info(String.format("EJB: %s created successfully", entity.getClass().getName()));
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
    @Override
    public User find(String login) throws ReadException{
        try {
            User user = (User) em.createNamedQuery("getUserLogin").setParameter("login", login).getSingleResult();
            return user;
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * The method to hash the password of the user
     * @param user The user with thte password without hashed
     * @return the user with the hashed password
     * @throws Exception if any error ocurred when hashing the password
     */
    public User hashUserPassword(User user) throws Exception{
        MessageDigest messageDigest = null;
        //Contraseña a hashear
        String hashedPassword = null;
        try {
            LOGGER.info("Hashing the password");
            messageDigest = MessageDigest.getInstance("MD5");
            
            byte dataBytes[] = user.getPassword().getBytes();
            messageDigest.update(dataBytes);
            
            byte resume[] = messageDigest.digest();
            hashedPassword = toHexString(resume);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("An error ocurred while hashing the password");
            throw new Exception("An error ocurred while hashing the password");
        }
       
        
        return user;
    }
    /**
     * A method to convert the resume in bytes to hexadecimal string
     * @param resume the resume in byte
     * @return the resume in string in hexadecimal
     */
    private String toHexString(byte[] resume){
        StringBuffer hashedPassword = new StringBuffer();
        
        for (byte b : resume) {
            hashedPassword.append(b);
        }
        
        return hashedPassword.toString();
    }

    @Override
    public void edit(User user) throws UpdateException {
        try {
            em.merge(user);
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void remove(User user) throws DeleteException {
         try {
            user = em.merge(user);
            em.remove(user);
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }
    
}
