/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import client.rest.CourseControllerREST;
import client.rest.SubjectControllerREST;

/**
 *This is the controller interfaces implementation factory
 * @author ioritz
 */
public class ControllerFactory {
    /**
     * Instanciates an subject controller interface implementation
     * @return An subject controller interface implementation
     */
    public static SubjectController getSubjectController(){
        return new SubjectControllerREST();
    }
    /**
     * Instanciates an CourseController interface implementation
     * @return An course controller interface implementation
     */
    public static CourseController getCourseController(){
        return new CourseControllerREST();
    }
}
