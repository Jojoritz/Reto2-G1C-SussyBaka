/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service;

import java.util.List;
import server.ejb.interfaces.UserEJBLocal;
import server.entities.User;
import server.exception.CreateException;
import server.exception.DeleteException;
import server.exception.ReadException;
import server.exception.UpdateException;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import server.entities.Course;
import server.entities.Student;
import server.entities.Subject;
import server.entities.Teacher;

/**
 *
 * @author ioritz
 */
@Path("entities.user")
public class UserFacadeREST{
    /**
     * EJB object with the busines logic
     */
    
    @EJB
    private UserEJBLocal ejb;
    /**
     * The logger of this class
     */
    private static final Logger LOGGER = Logger.getLogger(UserFacadeREST.class.getName());
    
    /**
     * POST method to create users
     * @param user the user with the data
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createUser(User user) {
        try {
            LOGGER.info("Creating a user");
            ejb.create(user);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    /**
     * PUT method to update user
     * @param user the data to update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void modifyUser(User user) {
        try {
            LOGGER.info("Modifying a user");
            ejb.edit(user);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    
     
    /**
     * DELETE method to remove the user
     * @param userId the data of user to remove
     */
    @DELETE
    @Path("user/userId/{userId}")
    public void removeUser(@PathParam("userId")Integer userId) {
        try {
            LOGGER.info("Deleting the user");
            ejb.remove(userId);
        } catch (DeleteException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
    /**
     * GET method to signIn a user
     * @param xtUser the data of the user to signIn
     * @return the user finded
     */
    @GET
    @Path("user/login/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User signIn(@PathParam("login")String login){
        User user = null;
        try {
            LOGGER.info("Searching the user");
            user = ejb.signIn(login);
            
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        
        return user;
        
    }
    /**
     * A get method to obtain all the users
     * @return a list with all the users
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll(){
        List<User> users = null;
        try {
            LOGGER.info("Searching the users");
            users = ejb.findAll();
            
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
        return users;
    }
    
    /**
     * A GET method to search a student and their relationships with the id
     * @param id the id of the student
     * @return the student with the relationships
     */
    @GET
    @Path("student/id/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
   public List<Course> getStudentRelationships(@PathParam("id")Integer id){
       List<Course> courses = null; 
       try {
            LOGGER.info("Searching the student with the id");
            courses = ejb.findStudentCourses(id);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
       return courses;
   }
   /**
    * A GET method to search a teacher and their relationships of subjects with the id
    * @param id the id of the teacher
    * @return the teacher with the relationships
    */
    @GET
    @Path("teacher/id/subjects/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
   public List<Subject> getTeacherSubjectRelationships(@PathParam("id")Integer id){
       List<Subject> subjects = null; 
       try {
            LOGGER.info("Searching the teacher with the id");
            subjects = ejb.findTeacherSubjects(id);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
       return subjects;
   }
   
   /**
    * A GET method to search a teacher and their relationships of courses with the id
    * @param id the id of the teacher
    * @return the teacher with the relationships
    */
    @GET
    @Path("teacher/id/courses/{login}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
   public List<Course> getTeacherCourseRelationships(@PathParam("id")Integer id){
       List<Course> courses = null; 
       try {
            LOGGER.info("Searching the teacher with the id");
            courses = ejb.findTeacherCourses(id);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
       return courses;
   }
}
