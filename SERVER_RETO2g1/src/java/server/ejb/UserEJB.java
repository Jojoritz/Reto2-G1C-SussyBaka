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

            Long count = this.findAll().stream()
                    .filter(user -> user.getLogin().equalsIgnoreCase(entity.getLogin())
                    && user.getEmail().equalsIgnoreCase(entity.getEmail())).count();

            if (count > 0) {
                throw new Exception("The user already exist");
            }

            LOGGER.info(String.format("User EJB: Creating %s", entity.getClass().getName()));
            if (!em.contains(entity)) {
                entity.setPassword(this.getHashMD5(entity.getPassword()));

                if (entity instanceof Teacher) {
                    Teacher teacher = (Teacher) entity;
                    Set<Subject> subjects = new HashSet<>();
                    Set<Course> courses = new HashSet<>();

                    teacher.getSpecializedSubjects().forEach((subject) -> {
                        if (subject.getSubjectId() != null) {
                            subjects.add(em.find(Subject.class, subject.getSubjectId()));
                        } else if (!em.contains(subject)) {
                            subjects.add(em.merge(subject));
                        }
                    });
                    if (!subjects.isEmpty()) {
                        teacher.setSpecializedSubjects(subjects);
                    }

                    teacher.getTeachingCourses().forEach((course) -> {
                        if (course.getCourseId() != null) {
                            courses.add(em.find(Course.class, course.getCourseId()));
                        } else if (!em.contains(course)) {
                            courses.add(em.merge(course));
                        }
                    });
                    if (!courses.isEmpty()) {
                        teacher.setTeachingCourses(courses);
                    }

                } else if (entity instanceof Student) {
                    Student student = (Student) entity;
                    List<Course> courses = new ArrayList<>();

                    student.getStudyingCourses().forEach((course) -> {
                        if (course.getCourseId() != null) {
                            courses.add(em.find(Course.class, course.getCourseId()));
                        } else if (!em.contains(course)) {
                            courses.add(em.merge(course));
                        }
                    });
                    if (!courses.isEmpty()) {
                        student.setStudyingCourses(courses);
                    }
                }

                em.persist(entity);
                LOGGER.info(String.format("User EJB: %s created successfully", entity.getClass().getName()));
            }

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public User signIn(String login, String password) throws ReadException {
        try {
            password = this.getHashMD5(password);
            User user = em.createNamedQuery("getUserLogin", User.class).
                    setParameter("login", login).
                    setParameter("password", password)
                    .getSingleResult();
            LOGGER.info("User EJB: Logging correct, Getting info of the user");
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
                LOGGER.info("User EJB: Find user and change password if its not the same (hashed)");
                User userFind = em.find(User.class, user.getId());
                if (!userFind.getPassword().equals(user.getPassword())) {
                    user.setPassword(getHashMD5(user.getPassword()));
                }

                if (user instanceof Teacher) {
                    LOGGER.info("User EJB: Merge specialized subjects from the teacher");
                    ((Teacher) user).getSpecializedSubjects().stream().filter((subject) -> (!em.contains(subject))).map((subject) -> {
                        em.merge(subject);
                        return subject;
                    }).forEachOrdered((subject) -> {
                        ((Teacher) userFind).getSpecializedSubjects().add(subject);
                    });

                    ((Teacher) user).setSpecializedSubjects(((Teacher) userFind).getSpecializedSubjects());
                } else if (user instanceof Student) {
                    LOGGER.info("User EJB: Merge studying courses from the student");
                    ((Student) user).getStudyingCourses().stream().filter((course) -> (!em.contains(course))).map((course) -> {
                        em.merge(course);
                        return course;
                    }).forEachOrdered((course) -> {
                        ((Student) userFind).getStudyingCourses().add(course);
                    });
                    ((Student) user).setStudyingCourses(((Student) userFind).getStudyingCourses());
                }
            }
            em.merge(user);
            em.flush();
            LOGGER.info("User EJB: Edit user successfully");
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void remove(Integer userId) throws DeleteException {
        try {
            LOGGER.info("User EJB: Find user");
            User user = find(userId);
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
            LOGGER.info(String.format("Searching for the user with id %d", id));
            User user = em.find(User.class, id);
            return user;
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
            return users;
        } catch (Exception e) {
            LOGGER.severe("An error happened when searching all the users");
            throw new ReadException("An error happened when searching all the users");
        }
    }

    @Override
    public void resetPassword(String email) throws UpdateException {
        try {

            LOGGER.info("Starting to reset the password");
            String randomPassword = MailSender.sendMail(email);

            User user = findAll().stream()
                    .filter(users -> users.getEmail().equals(email)).findFirst().orElse(null);

            if (user == null) {
                throw new Exception();
            }
            user.setPassword(getHashMD5(randomPassword));
            this.edit(user);

        } catch (Exception e) {
            LOGGER.severe("An error happened when reseting the password");
            throw new InternalServerErrorException("An error happened when reseting the password");
        }
    }

}
