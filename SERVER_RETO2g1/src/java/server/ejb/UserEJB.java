/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.ejb;

import server.ejb.interfaces.UserEJBLocal;
import server.entities.Student;
import server.entities.Teacher;
import server.entities.User;
import server.exception.CreateException;
import server.exception.ReadException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import server.entities.enumerations.UserPrivilege;
import server.exception.DeleteException;
import server.exception.UpdateException;

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
        Integer idExist = null;
        try {
            LOGGER.info("Searching if the user exist");
            entity.setPassword(hashUserPassword(entity.getPassword()));

            try {
                idExist = (Integer) em.createNamedQuery("getUserId").setParameter("login", entity.getLogin())
                        .setParameter("password", entity.getPassword()).getSingleResult();
                throw new Exception("The user all ready exist");
            } catch (NoResultException e) {
                LOGGER.severe("Llega a despues de la comprobacion, creando usuario");
                LOGGER.info(String.format("EJB: Creating %s", entity.getClass().getName()));
                if (entity.getPrivilege().equals(UserPrivilege.TEACHER) && ((Teacher)entity).getSpecializedSubjects() != null) {
                    ((Teacher)entity).setSpecializedSubjects(
                            em.merge(
                                    ((Teacher)entity).getSpecializedSubjects()
                            )
                            
                    );
                }
                if (entity.getPrivilege().equals(UserPrivilege.STUDENT) && ((Student)entity).getStudyingCourses()!= null) {
                    ((Student)entity).setStudyingCourses(
                            em.merge(
                                    ((Student)entity).getStudyingCourses()
                            )
                            
                    );
                }
                em.persist(entity);
                LOGGER.info(String.format("EJB: %s created successfully", entity.getClass().getName()));
                
            }

            

            
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public User signIn(String login) throws ReadException {
        try {
            Integer userId = (Integer) em.createNamedQuery("getUserId").setParameter("login", login).getSingleResult();
            User user = em.find(User.class, userId);
            return user;
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * The method to hash the password of the user
     *
     * @param user The password without hashed
     * @return the hashed password
     * @throws Exception if any error ocurred when hashing the password
     */
    private String hashUserPassword(String password) throws Exception {
        MessageDigest messageDigest = null;
        //Contraseña a hashear
        String hashedPassword = null;
        try {
            LOGGER.info("Hashing the password");
            messageDigest = MessageDigest.getInstance("MD5");

            byte dataBytes[] = password.getBytes();
            messageDigest.update(dataBytes);

            byte resume[] = messageDigest.digest();
            hashedPassword = toHexString(resume);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("An error ocurred while hashing the password");
            throw new Exception("An error ocurred while hashing the password");
        }

        return hashedPassword;
    }

    /**
     * A method to convert the resume in bytes to hexadecimal string
     *
     * @param resume the resume in byte
     * @return the resume in string in hexadecimal
     */
    private String toHexString(byte[] resume) {
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
            LOGGER.severe(e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void remove(Integer user_id) throws DeleteException {
        try {
            User user = em.find(User.class, user_id);
            user = em.merge(user);
            em.remove(user);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public List<User> findAll() throws ReadException {
        try {
            LOGGER.info("Searching for all the users");
            List<User> users = em.createNamedQuery("findAllUsers").getResultList();
            LOGGER.info("user llege aqui");
            LOGGER.info(users.toString());
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.severe("An error happened when searching all the users");
            throw new ReadException("An error happened when searching all the users");
        }
    }
    
   

}
