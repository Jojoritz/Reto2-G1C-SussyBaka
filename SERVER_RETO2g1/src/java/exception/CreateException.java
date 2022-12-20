/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * This class extends from {@link Exception} and its used to catch exceptions
 * when creating/inserting data
 *
 * @author yeguo
 */
public class CreateException extends Exception {

    /**
     * Empty constructor of the {@link CreateException}
     */
    public CreateException() {
    }

    /**
     * Constructor of the {@link CreateException} with message
     * 
     * @param message Message with details of the exception
     */
    public CreateException(String message) {
        super(message);
    }

}
