/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import java.math.BigInteger;
import server.ejb.interfaces.UserEJBLocal;
import server.entities.User;
import server.exception.CreateException;
import server.exception.ReadException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.InternalServerErrorException;
import server.entities.Course;
import server.entities.Student;
import server.entities.Subject;
import server.entities.Teacher;
import server.exception.DeleteException;
import server.exception.UpdateException;
import server.service.mail.MailSender;

/**
 *
 * @author ioritz
 */
@Stateless
public class UserEJB implements UserEJBLocal {

    private static final Logger LOGGER = Logger.getLogger(UserEJB.class.getName());

    @PersistenceContext(unitName = "JavaFX-WebApplicationUD5ExamplePU")
    private EntityManager em;

    @Override
    public void create(User entity) throws CreateException {

        try {
            LOGGER.info("Searching if the user exist");

            try {
                User userExists = em.createNamedQuery("getUserLogin", User.class).setParameter("login", entity.getLogin())
                        .setParameter("password", entity.getPassword()).getSingleResult();
                if (userExists != null) {
                    throw new Exception("The user already exist");
                }
            } catch (NoResultException e) {
                LOGGER.info(String.format("EJB: Creating %s", entity.getClass().getName()));
                if (!em.contains(entity)) {
                    entity.setPassword(this.getHashMD5(entity.getPassword()));

                    if (entity instanceof Teacher) {
                        Teacher teacher = (Teacher) entity;
                        Set<Subject> subjects = new HashSet<>();
                        Set<Course> courses = new HashSet<>();

                        for (Subject subject : teacher.getSpecializedSubjects()) {
                            if (subject.getSubjectId() != null) {
                                subjects.add(em.find(Subject.class, subject.getSubjectId()));
                            } else if (!em.contains(subject)) {
                                subjects.add(em.merge(subject));
                            }
                        }
                        if (!subjects.isEmpty()) {
                            teacher.setSpecializedSubjects(subjects);
                        }

                        for (Course course : teacher.getTeachingCourses()) {
                            if (course.getCourseId() != null) {
                                courses.add(em.find(Course.class, course.getCourseId()));
                            } else if (!em.contains(course)) {
                                courses.add(em.merge(course));
                            }
                        }
                        if (!courses.isEmpty()) {
                            teacher.setTeachingCourses(courses);
                        }

                    } else if (entity instanceof Student) {
                        Student student = (Student) entity;
                        List<Course> courses = new ArrayList<>();

                        for (Course course : student.getStudyingCourses()) {
                            if (course.getCourseId() != null) {
                                courses.add(em.find(Course.class, course.getCourseId()));
                            } else if (!em.contains(course)) {
                                courses.add(em.merge(course));
                            }
                        }
                        if (!courses.isEmpty()) {
                            student.setStudyingCourses(courses);
                        }
                    }

                    em.persist(entity);
                    LOGGER.info(String.format("EJB: %s created successfully", entity.getClass().getName()));
                }

            }

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public User signIn(String login, String password) throws ReadException {
        try {
            User user = em.createNamedQuery("getUserLogin", User.class).
                    setParameter("login", login).
                    setParameter("password", password)
                    .getSingleResult();
            LOGGER.info(getHashMD5(password));
            return user;
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * The method to hash a text, this is used to hash the password
     *
     * @param user The password without hashed
     * @return the hashed password
     * @throws Exception if any error when hashing the password
     */
    private String getHashMD5(String textToHash) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(textToHash.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e);
        }

    }

    @Override
    public void edit(User user) throws UpdateException {
        try {
            if (!em.contains(user)) {
                User userFind = em.find(User.class, user.getId());
                if (!userFind.getPassword().equals(user.getPassword())) {
                    user.setPassword(getHashMD5(user.getPassword()));
                }

                if (user instanceof Teacher) {
                    for (Subject subject : ((Teacher) user).getSpecializedSubjects()) {
                        if (!em.contains(subject)) {
                            em.merge(subject);
                            ((Teacher) userFind).getSpecializedSubjects().add(subject);
                        }
                    }

                    ((Teacher) user).setSpecializedSubjects(((Teacher) userFind).getSpecializedSubjects());
                } else if (user instanceof Student) {
                    for (Course course : ((Student) user).getStudyingCourses()) {
                        if (!em.contains(course)) {
                            em.merge(course);
                            ((Student) userFind).getStudyingCourses().add(course);
                        }
                    }
                    ((Student) user).setStudyingCourses(((Student) userFind).getStudyingCourses());
                }
            }
            em.merge(user);
            em.flush();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void remove(Integer user_id) throws DeleteException {
        try {
            User user = find(user_id);
            user = em.merge(user);
            em.remove(user);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public User find(Integer id) throws ReadException {
        try {
            LOGGER.info("Searching for all the users");
            return em.find(User.class, id);
        } catch (Exception e) {
            LOGGER.severe("An error happened when searching all the users");
            throw new ReadException("An error happened when searching all the users");
        }
    }

    @Override
    public List<User> findAll() throws ReadException {
        try {
            LOGGER.info("Searching for all the users");
            List<User> users = em.createNamedQuery("findAllUsers").getResultList();
            LOGGER.info("user llege aqui");
            return users;
        } catch (Exception e) {
            LOGGER.severe("An error happened when searching all the users");
            throw new ReadException("An error happened when searching all the users");
        }
    }

    @Override
    public void resetPassword(String email) throws UpdateException {
        User user = null;
        try {
            
            LOGGER.info("Starting to reset the password");
            String randomPassword = MailSender.sendMail(email);
            
            List<User> users = findAll();
            
            for (int i = 0; i < users.size() && user == null; i++) {
                if (users.get(i).getEmail().equals(email)) {
                    user = users.get(i);
                }
            }
            user.setPassword(getHashMD5(randomPassword));
            edit(user);
            
        } catch (Exception e) {
            LOGGER.severe("An error happened when reseting the password");
            throw new InternalServerErrorException("An error happened when reseting the password");
        }
    }

    

}
