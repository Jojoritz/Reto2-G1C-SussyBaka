/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 * This class extends from {@link Exception} and its used to catch exceptions
 * when updating/modifying data
 *
 * @author yeguo
 */
public class UpdateException extends Exception {

    /**
     * Empty constructor of the {@link UpdateException}
     */
    public UpdateException() {
    }

    /**
     * Constructor of the {@link UpdateException} with message
     *
     * @param message Message with details of the exception
     */
    public UpdateException(String message) {
        super(message);
    }
}
