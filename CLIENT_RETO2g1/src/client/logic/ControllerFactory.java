/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import client.rest.CommentControllerREST;
import client.rest.CourseControllerREST;
import client.rest.PostControllerRest;
import client.rest.SubjectControllerREST;
import client.rest.UserControllerREST;

/**
 * This is the controller interfaces implementation factory
 *
 * @author ioritz
 */
public class ControllerFactory {

    private static SubjectController subjectController;
    private static CourseController courseController;
    private static UserController userController;
    private static PostController postController;
    private static CommentController commentController;

    /**
     * Instantiate an subject controller interface implementation
     *
     * @return An subject controller interface implementation
     */
    public static SubjectController getSubjectController() {
        if (subjectController == null) {
            subjectController = new SubjectControllerREST();
        }
        return subjectController;
    }

    /**
     * Instantiate an CourseController interface implementation
     *
     * @return An course controller interface implementation
     */
    public static CourseController getCourseController() {
        if (courseController == null) {
            courseController = new CourseControllerREST();
        }

        return courseController;
    }

    /**
     * Instantiate a user controller interface implementation
     *
     * @return A user controller interface implementation
     */
    public static UserController getUserController() {
        if (userController == null) {
            userController = new UserControllerREST();
        }
        return userController;
    }

    /**
     * Instantiate a post controller interface implementation
     *
     * @return A post controller interface implementation
     */
    public static PostController getPostController() {
        if (postController == null) {
            postController = new PostControllerRest();
        }
        return postController;
    }

    /**
     * Instantiate a comment controller interface implementation
     *
     * @return A comment controller interface implementation
     */
    public static CommentController getCommentController() {
        if (commentController == null) {
            commentController = new CommentControllerREST();
        }
        return commentController;
    }
}
