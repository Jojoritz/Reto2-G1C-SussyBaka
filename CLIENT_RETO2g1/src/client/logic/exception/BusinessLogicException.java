/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic.exception;

/**
 *  Exception threw on the business logic layer of the application
 * 
 * @author Henri
 */
public class BusinessLogicException extends Exception {

    public BusinessLogicException(String message) {
        super(message);
    }

}
