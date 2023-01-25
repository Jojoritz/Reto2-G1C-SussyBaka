/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import client.rest.SubjectControllerREST;

/**
 *
 * @author iorit
 */
public class ControllerFactory {
    public static SubjectController getSubjectController(){
        return new SubjectControllerREST();
    }
}
