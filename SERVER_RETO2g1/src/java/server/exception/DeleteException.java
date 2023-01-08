/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.exception;

/**
 * This class extends from {@link Exception} and its used to catch exceptions
 * when deleting/removing data
 *
 * @author yeguo
 */
public class DeleteException extends Exception{

    /**
     * Empty constructor of the {@link DeleteException}
     */
    public DeleteException() {
    }

    /**
     * Constructor of the {@link DeleteException} with message
     * 
     * @param message Message with details of the exception
     */
    public DeleteException(String message) {
        super(message);
    }
    
}
